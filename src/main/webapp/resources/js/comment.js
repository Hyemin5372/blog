$(document).ready(function() {
      
      
    // ======================================================================================================================= >
    // callComments 함수에
          function callComments() {
          
             $.ajax({
                
                url : "readcomment" ,
                method : "GET" ,
                data: { "board_id": board_id },
                success : function( data ) {
                   
                   console.log( "댓글갯수 => " + data.length );			
                   var row = `<h2>댓글 ${data.length}개</h2>`;
                               
                   
                   for (var i=0; i < data.length; i++) {
                
                      
                      //지금 로그인한 유저와 댓글 작성자가 같냐
                      if ( user_id == data[i].user_id ) {
    
                      //그럼 row에 그 댓글에 수정/삭제를 보여줘라
                         row += getComponent(data[i], "block");
                      } else {
    
                      //아니면 없에라
                         row += getComponent(data[i], "none");
                      }
                   }
                   

                
                   //html==append랑 같은건데 밑에 getComponent 함수의 모든 div값을 넣은 것 이다
                   $(".content-footer").html( row );
                   
                   // 댓글 수정버튼 눌렀을때 작동할 내용
                   $(".comment-modify").click(function() {
                       if($(this).text().indexOf("수정")>-1){
                       
                       //addClass("form-control"):아웃라인을 만들어라
                       //.comment =>댓글
                       $("#commentIdentity" + $(this).attr("id")).find("textarea").addClass("form-control");
                       
                        //prop("readonly", false) :읽기 전용을 없에라
                       $("#commentIdentity" + $(this).attr("id")).find("textarea").prop("readonly", false);
                        
                        $("#commentIdentity" + $(this).attr("id")).find("textarea").css({"border" : "1px solid #ccc" , "outline" : "initial", "cursor" : "text"});
    
                   //내 자신의 text(타입)에 저장을 만들어라
                   $(this).text("저장");
                   
                   //그 다음의 text(타입)에 취소를 만들어라
                         $(this).next().text("취소");
                         
                   //내 자신".comment-edit"text가 indexOf(인덱스)-1보다 큰것이 저장이냐
                   } else if ( $(this).text().indexOf("저장") > -1 ) {
                      var comment_id = $(this).attr("id");
                      
                      
                      $.ajax({
                      
                         url : "editcomment?comment_id="+comment_id ,
                         method : "POST" ,
                         data :{
                         "comment_id" : $(this).attr("id") ,
                         //commentIdentity 가지고 있는 자신.수정버튼이 가지고 있는 id(한마디로 pk값)+" 자식요소가textarea"인=>그래서 앞에 한칸을 띄움 값을 content에 넣어라.
                         "content"  : $("#commentIdentity" + $(this).attr("id")).find("textarea").val()
                         }
                         ,success : function() {
                            callComments();
                         }
                      }) // ~ ajax 끝 !
                      }
                   }) // ~ 댓글 수정버튼 이벤트 끝 !
                   
    
    
                   
                   
                   $(".comment-delete").click(function() {
                      // 댓글 삭제버튼 눌렀을때 작동할 내용 attr("id") => id의 속성 값을 가지고 오는 거임
                      var comment_id = $(this).attr("id");
                      if($(this).text().indexOf("취소")>-1){
                      
                            callComments();
                      }else{ $.ajax({
    
                      //url이 delete인이유는 컨트롤러에 있는 매칭되는 delete를 실행시키는거임 하지만 ajax이기때문에 눈에 보이진 않는 거임
                         url : "deletecomment?comment_id="+comment_id ,
                         method : "GET" ,
                         
                         success : function() {
    
                            callComments();
    
                                  // 댓글 삭제에 성공했으면, 변경된 내용을 다시 받아와서 화면에 표시함
                         }
                      }) // ~ ajax 끝 !
                      }
                                        
                   }) // ~ 댓글 삭제버튼 이벤트 끝 !
    
                   
    
    
    
                   // ★ 댓글 작성버튼 눌렀을때 (AJAX)
                   $("#comment-write").off("click");
                   $("#comment-write").click(function (){
                      alert('test');
                            $.ajax({
    
                               //url이 write인이유는 컨트롤러에 있는 매칭되는 write를 실행시키는거임 하지만 ajax이기때문에 눈에 보이진 않는 거임
                               url : "writecomment?board_id=" + board_id
                               , type : "POST"
                               
                               //val은 input타입에 입력되어 있는 값을 사용할때(뭐라 적혀있는 지 알고 싶을 때)
                               , data : { "content" : $("#ContentComment").val() }
                               
                               , success: function() {
    
                                  // 댓글 작성에 성공했으면, 변경된 내용을 다시 받아와서 화면에 표시함
                                  callComments();
                               }
                            })
                            
                      
                   }) // ~ 댓글 작성버튼 이벤트 끝 !
    
                   
                          
                         //=> 댓글 입력란 이벤트 등록(자동 높이 조절)
                         
                         // $(this).attr("id") => $(".comment-reply") 이게 this가 comment-reply(답글버튼)임 그리고 거기에 id값이 identity임 그걸 identity에 넣어 다시 가지고 옴 
                         var identity = $(this).attr("id");
    
                
 
 
                 }
             })		
          
          }
          
          
    // ======================================================================================================================= >		
    
    //	댓글 =>getComponent 함수에 data 매게변수를 넣어줌
    //	show=>위에서 받은 display가 block 또는 none임
         function getComponent(data, show) {
    var row = ``;
     row +=  `
             <div id="commentIdentity${data.comment_id}" class="comment-wrapper mt-5">
             <div class="comment">
                <div class="comment-header" style="display: flex;">
                   
                   <div class="profileInfo">
                         <h4 style="display: inline;">${data.username}</h4>
                         <sub style="display: inline; color: black;">${data.created}</sub>
                   </div>
                </div>
                
                <textarea
                   id = "${data.comment_id}" 
                   class = "modify-textarea"  
                   readonly 
                   style="margin: 20px 0; border:none; outline:none; display: block; cursor: default; width: 100%" >${data.content}</textarea>
                   <div class="comment-footer">
                `;	
                
               if ( user_id == data.user_id ) {
                   // 로그인 + 작성자 본인일때
                   row += `
                         <button type="button" id="${data.comment_id}" class="comment-modify" style=" border: 1px solid #eee;">
                         수정</button>
                         <button type="button" id="${data.comment_id}" class="comment-delete" style=" border: 1px solid #eee;">
                         삭제</button>
                         
                   `;
    
                }
    
 
    row += `</div></div></div>`;
    return row;
 }
 
          
    
    
    // ======================================================================================================================= >
    
       // ★ : 페이지 로딩되면 최초 1회 댓글목록 불러오기 ( 이후 댓글 작성버튼 눌러서 추가된 이후에도 다시 호출해서 변경내용 보여줘야함 )
       callComments();
    
    })// ~~ 자바스크립트 끝남