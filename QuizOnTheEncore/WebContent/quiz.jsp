<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.tooltip {
  position: relative;
  display: inline-block;
  border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 120px;
  background-color: black;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;
  
  /* Position the tooltip */
  position: absolute;
  z-index: 1;
  top: -5px;
  right: 105%;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
}
</style>
<head>
<meta charset="UTF-8">
<title>Play Quiz</title>

<!-- GLOBAL WRAPPER CSS -->
  <link rel="stylesheet" type="text/css" href="//www.fedex.com/css/t2/global-wrapper-min.css" id="globalCSS" />
  <!-- MASTER RESPONSIVE CSS -->
  <link rel="stylesheet" type="text/css" href="//www.fedex.com/css/t2/master-responsive-min.css" />
   <link rel="stylesheet" type="text/css" href="css/quiz.css">

</head>
<body>
   <body id="fx-respond">
  
  <div id="container">
    <div id="content" class="fx-tiles">
      <!-- FULL WIDTH LAYOUT 하얀 배경 조정-->
      <div class="fx-grid fx-cf">
        <div class="fx-col"><!-- 문제들 간격 조정 -->
          <!-- ADD BLOCK(S) HERE -->
          <!-- START: SHARE BLOCK -->
          <div class="fx-block fx-purple fx-share fx-align-left fx-cf purple-bg" style="padding:20px ">
            <div class="fx-copy" style="padding-left:20px; padding-bottom:0">
              <h2>용퀴즈 온더 블록!</h2>
              <p>저희 퀴즈 프로그램에 오신 분들 환영합니다~ <br>저희의 퀴즈 주제는 영화입니다 잘 보시고 선택해 주세요 ٩(๑>∀<๑)۶ </p>
              <p><a href="#game" id="start-button" class="fx-btn">시작하기</a></p>
            </div>
          </div>
        </div>
        
        <!-- FULL WIDTH LAYOUT -->
        <div class="fx-grid fx-cf">
          <div class="fx-col">
            <!-- ADD BLOCK(S) HERE -->
            <div class="fx-block game-container">
              <div id="game">
                <div class="game-grid">
                  <p id="timer" class="timer">
                     00:00:00
                  </p>
                  <div class="game-left-col">
                    <div class="gauge-container" style="background: #ffffff url('images/icon-01.svg') no-repeat center center; background-size: 100px 100px;">
                      <canvas id="gauge" class="gauge" width="240" height="240"></canvas>
                      <div class="score-number">0</div>
                    </div>
                  </div>
                  <div class="game-right-col">
                    <div class="questions">
                    <!-- 역대 한국영화 중 누적관객수가 많은 순서대로 나열하세요 -->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[0].quizNumber}</span><span class="questionText">${requestScope.quizAll[0].question}</span></p>
                        <div class="answers">
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">1. 명량 - 국제시장 - 극한직업 - 신과함게 - 도둑들</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">2. 국제시장 - 명량 - 신과함께 - 도둑들 - 극한직업</span></label>
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">3. 명량 - 극한직업 - 국제시장 - 베테랑 - 도둑들</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">4. 국제시장 - 극한직업 - 명량 - 베테랑 - 신과함께</span></label>
                  <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">모두 1000만 관객 이상 영화입니다요 :)</span>
                        </div>
                        </div>
                      </div>
                      <!-- <순서 퀴즈>다음 영화 중 러닝타임이 가장 짧은 영화는 무엇일까요 -->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[1].quizNumber}</span><span class="questionText">${requestScope.quizAll[1].question}</span></p>
                        <div class="answers">
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">1. 곡성</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">2. 박쥐</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">3. 옥자</span></label>
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">4. 부산행</span></label>
                          <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">한 영화만 120분 미만입니다요 :)</span>
                        </div>
                        </div>
                      </div>
                       <!-- <소리 퀴즈>다음 소리를 듣고 무슨 영화인지 맞춰보세요 -->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[2].quizNumber}</span><span class="questionText">${requestScope.quizAll[2].question}</span></p>
                        <div class="answers">
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">1. 올드보이</span></label>
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">2. 황해</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">3. 범죄와의 전쟁</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">4. 범죄도시</span></label>
                          <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">바삭바삭합니다 때론 습기를 머금기도.. ◖⚆ᴥ⚆◗</span>
                        </div>
                        </div>
                      </div>
                       <!-- <초성 퀴즈>다음 초성을 보고 무슨 영화인지 맞춰보세요 -->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[3].quizNumber}</span><span class="questionText">${requestScope.quizAll[3].question}</span></p>
                        <div class="answers">
                        <img src="image/note.jpg">
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">1. 데스노트</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">2. 독서노트</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">3. 다시나태</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">4. 도시낙타</span></label>
                          <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">하늘에서 공책이 떨어집니다</span>
                        </div>
                        </div>
                      </div>
                       <!-- <이미지 퀴즈>다음 사진을 보고 영화 제목을 맞추세요 -->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[4].quizNumber}</span><span class="questionText">${requestScope.quizAll[4].question}</span></p>
                        <div class="answers">
                        <img src="image/dark.jpg" alt="darknight">
                        <img src="image/joker.jpg" alt="joker">
                        <img src="image/asura.jpg" alt="asura">
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">1. 다크엔젤</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">2. 마징가 z</span></label>
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">3. 다크나이트</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">4. 히든페이스</span></label>
                          <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">저의 인생영화 입니다 :)</span>
                        </div>
                        </div>
                      </div>
                       <!-- <초성 퀴즈>다음 초성을 보고 영화 제목을 맞추세요 -->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[5].quizNumber}</span><span class="questionText">${requestScope.quizAll[5].question}</span></p>
                        <div class="answers">
                        <img src="image/sport.jpg" alt="sport" height="300" width="450">
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">1. 재규어</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">2. 전기어</span></label>
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">3. 족구왕</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">4. 장기알</span></label>
                          <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">스포츠영화입니다 :)</span>
                        </div>
                        </div>
                      </div>
                       <!-- <이미지 퀴즈>다음 부분을 보고 영화 제목을 맞추세요 -->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[6].quizNumber}</span><span class="questionText">${requestScope.quizAll[6].question}</span></p>
                        <div class="answers">
                        <img src="image/narnia2.jpg" alt="narnia" height="300" width="450">
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">1. 나니아 연대기</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">2. 쥬만지</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">3. 월터의 상상은 현실이 된다</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">4. 브루스 올마이티</span></label>
                          <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">사자가 말을 해요! (ʘᗩʘ’) </span>
                        </div>
                        </div>
                      </div>
                       <!-- <이미지 퀴즈>다음 배우가 출연 하지 않은 작품을 맞추세요-->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[7].quizNumber}</span><span class="questionText">${requestScope.quizAll[7].question}</span></p>
                        <div class="answers">
                        <img src="image/actor.jpg" alt="actor" height="300" width="450">
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">1. 도깨비</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">2. 여배우는 너무해</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">3. 내부자들</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">4. 바람바람바람</span></label>
                          <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">저희 주제를 잘 확인하세요!! (￣(oo)￣)ﾉ </span>
                        </div>
                        </div>
                      </div>
                       <!-- <소리 퀴즈>다음 영화의 OST가 아닌 것은? -->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[8].quizNumber}</span><span class="questionText">${requestScope.quizAll[8].question}</span></p>
                        <div class="answers">
                        <img src="image/beginagain.jpg" alt="song" height="300" width="450">
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">1. Lost stars</span></label>
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">2. To find You</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">3. Coming up roses</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">4. No one Else Like You</span></label>
                          <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">저도 어렵답니다  ＿|￣|○､;’.･</span>
                        </div>
                        </div>
                      </div>
                       <!-- <이미지 퀴즈>다음 사진들을 보고 영화 제목을 맞추세요 -->
                      <div class="question">
                        <p><span class="number">${requestScope.quizAll[9].quizNumber}</span><span class="questionText">${requestScope.quizAll[9].question}</span></p>
                        <div class="answers">
                         <img src="image/king.jpg" alt="kingworld" height="300" width="450">
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">1. 혈의 누</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">2. 남극일기</span></label>
                          <label><input type="checkbox" class="answer" data-correct="false"><span class="text">3. 용서받지 못한 자</span></label>
                          <label><input type="checkbox" class="answer" data-correct="true"><span class="text">4. 왕의 남자</span></label>
                          <div class="tooltip" style="float:right">힌트
                        	<span class="tooltiptext">2005년 영화 입니다  ጿኈ ቼ ዽ ጿ</span>
                        </div> 
                        </div>
                      </div>
                    </div>
                    <div id="game-end-times-up">
                      <p id="time-out-text" class="game-end-text"></p>
                      <a href="quiz.jsp" type="button" class="fx-btn">Play again</a>

                    </div>
                    <div id="game-end">
                      <p id="game-end-text" class="game-end-text"></p>
                      
                      <form action="quiz" method="post">
                      <input type="hidden" id="hiddenName" name="hiddenName" value="${sessionScope.quizerId}">
                      <input type="hidden" id="hiddenTimer" name="hiddenTimer">
                      <input type="hidden" id="hiddenScore" name="hiddenScore">
                      <input type="button" class="fx-btn" value="Go My Detail" onclick="location.href='?command=quizerDetail'">
                      </form>
                      
                      <%-- <form action="quiz" method="post">
                      <input type="hidden" name="quizerId" value="${sessionScope.quizerId}">
					<input type="hidden" name="password" value="${sessionScope.password}">
					<input type="hidden" name="command" value="quizerLogin">
                      <input type="submit" class="fx-btn" value="Go My Detail">
                      </form> --%>
                      <!-- <a href="quiz.jsp" id="sendResult" type="button" class="fx-btn">Play again</a> -->
                    </div>
                  </div>
                </div>
                
                
                <div id="progress-bar" class="progress-bar">
                  <div class="indicator current"></div>
                  <div class="indicator"></div>
                  <div class="indicator"></div>
                  <div class="indicator"></div>
                  <div class="indicator"></div>
                  <div class="indicator"></div>
                  <div class="indicator"></div>
                  <div class="indicator"></div>
                  <div class="indicator"></div>
                  <div class="indicator"></div>
                </div>
              </div>
            </div>
          </div>
        </div>  
       </div>
      </div>
     </div>
 
  <!-- MASTER RESPONSIVE JAVASCRIPT (Keep below footer include) -->
  <script type="text/javascript" src="//images.fedex.com/templates/components/libraries/1.0/jQuery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="//www.fedex.com/templates/components/javascript/v2/fx-master-responsive.js"></script>
  
  <script src="js/quiz.js"></script>
</body>
</body>
</html>