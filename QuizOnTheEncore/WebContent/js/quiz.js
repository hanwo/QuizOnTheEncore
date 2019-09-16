//#은 id, .은 class

var game = {
  // state and jquery selectors
  state: {
    startButton: $("#start-button"),
    gameContainer: $("#game"),
    scoreNumber: $(".score-number"),
    questionsView: $(".questions"),
    gameEndView: $("#game-end"),
    gameEndViewTimesUp: $("#game-end-times-up"),
    gameEndText: $("#game-end-text"),
    timeOutText: $("#time-out-text"),
    gauge: $("#gauge"),
    questions: $(".question"),
    answers: $(".answer"),
    timer: $("#timer"),
    indicators: $(".indicator"),
    numberOfQuestions: $(".question").length,
    questionsAnswered: 0,
    correctAnswers: 0
  },

  init: function() {
    game.registerEventHandlers();
  },

  registerEventHandlers: function() {
    game.state.answers.on("click touch", function(e) {
      //e.preventDefault();
      game.checkAnswer($(this));
    });

    game.state.startButton.on("click touch", function(e) {
      e.preventDefault();
      game.start();
    });
  },

  start: function() {
    game.state.gameContainer.addClass("show");
    $("html, body").animate(
      {
        scrollTop: game.state.gameContainer.offset().top - 10
      },
      400,
      game.startTimer()
    );
    game.state.startButton.unbind("click touch");
  },

  startTimer: function() {
    var zeroFill = function(units) {
      return units < 10 ? "0" + units + "" : units;
    };
    var count = 0;

    var interval = window.setInterval(function() {
      var centisecondsRemaining = 12000 - count;
      var min = Math.floor(centisecondsRemaining / 100 / 60);
      var sec = zeroFill(Math.floor(centisecondsRemaining / 100 % 60));
      var cs = zeroFill(centisecondsRemaining % 100);
      game.state.timer.text(min + ":" + sec + ":" + cs);
            count++;
      if (centisecondsRemaining === 0) {
        clearInterval(interval);
        //game.endGame();
        game.timesUp();
      }
      
      if (game.state.questionsAnswered === game.state.numberOfQuestions) {
         document.getElementById("hiddenTimer").value = min + ":" + sec + ":" + cs;
         console.log("1111111111111111111111111111")
         console.log(document.getElementById("hiddenTimer").value);
        
        clearInterval(interval);
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
           console.log("2222222222222222222222222")
           console.log("hi1");
          if (this.readyState == 4 && this.status == 200) {
             //console.log("bye");
          //alert("입력이 완료되었습니다");
          }
        };
   var storeTime = document.getElementById("hiddenTimer").value;
   var hiddenName = document.getElementById("hiddenName").value;
   console.log("333333333333333333333333")
   console.log(hiddenName);
   document.getElementById("hiddenScore").value = game.state.correctAnswers *10;
   var fscore = document.getElementById("hiddenScore").value;
   console.log("44444444444444444444444")
   console.log(fscore);

   //console.log(typeof(storeTime));
   //xhttp.open("POST","quiz?command=quizTimeInsert&storeTime="+storeTime+"%quizerId="+quizerId,true);
   xhttp.open("POST","quiz?command=updateQuizerResult&storeTime="+storeTime+"&quizerId="+hiddenName+"&fscore="+fscore,true);
   xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   xhttp.send();
      }
    }, 1);
  },

  
  
  checkAnswer: function(answer) {
    if (answer.data("correct")) {
      game.state.correctAnswers++;
      game.drawGaugeValue();
      game.updateProgress(true);
      game.giveAnswerFeedback(answer);
    } else {
      game.updateProgress(false);
      game.giveAnswerFeedback(answer);
    }
    game.state.questionsAnswered++;

    // wait a second
    window.setTimeout(function() {
      //end game else go to next question
      if (game.state.questionsAnswered === game.state.numberOfQuestions) {
        game.endGame();
        
      } else {
        game.goToNextQuestion();
      }
    }, 100);
  },

  drawGaugeValue: function() {
    var currentValue =
      100 /
      (game.state.numberOfQuestions / (game.state.correctAnswers - 1)) /
      100;
    var nextValue = currentValue + 100 / game.state.numberOfQuestions / 100;

    var draw = function(currentValue, nextValue) {
      var bg = game.state.gauge[0];
      var ctx = (ctx = bg.getContext("2d"));
      var imd = null;

      var startRad = 0.75;
      var totalRads = 1.5;

      var sAngle = Math.PI * startRad;

      ctx.beginPath();
      ctx.strokeStyle = "#47338f";
      ctx.lineCap = "round";
      ctx.closePath();
      ctx.fill();
      ctx.lineWidth = 10.0;

      imd = ctx.getImageData(0, 0, 240, 240);

      $({ n: currentValue }).animate(
        { n: nextValue },
        {
          duration: 1000 * nextValue,
          step: function(now, fx) {
            game.state.scoreNumber.text(Math.ceil(now * 100));
            ctx.putImageData(imd, 0, 0);
            ctx.beginPath();
            ctx.arc(
              120,
              120,
              100,
              sAngle,
              (totalRads * now + startRad) * Math.PI,
              false
            );
            ctx.stroke();
          }
        }
      );
    };
    draw(currentValue, nextValue);
  },

  updateProgress: function(correct) {
    $(game.state.indicators[game.state.questionsAnswered]).addClass(
      correct ? "correct" : "incorrect"
    );
    $(game.state.indicators).removeClass("current");
    $(game.state.indicators[game.state.questionsAnswered + 1]).addClass(
      "current"
    );
  },

  giveAnswerFeedback: function(answer) {
    var answerGroup = answer.parent().parent().find(".answer");
    for (var i = 0; i < answerGroup.length; i++) {
      // disable extra clicks
      answerGroup[i].disabled = true;
      if ($(answerGroup[i]).data("correct")) {
        $(answerGroup[i]).parent().addClass("correct");
      } else {
        $(answerGroup[i]).parent().addClass("incorrect");
      }
    }
  },

  goToNextQuestion: function() {
    var lastQuestionIndex = game.state.questionsAnswered - 1;
    var nextQuestionIndex = game.state.questionsAnswered;
    $(game.state.questions[lastQuestionIndex]).fadeOut(100, function() {
      $(game.state.questions[nextQuestionIndex]).fadeIn(100);
    });
  },
  timesUp: function() {
    var endText =
      "시간이 다 됬습니다~<br />오늘은 여기서 그만~ 내일 봐요~~.";
    game.state.questionsView.fadeOut(400, function() {
      game.state.timeOutText[0].innerHTML = endText;
      game.state.gameEndViewTimesUp.fadeIn(200);
    });
  },

  endGame: function() {
    var endText =
      'You got<br><span class="score">' +
      game.state.correctAnswers +
      " out of " +
      game.state.numberOfQuestions +
      "</span><br> correct";
    //alert(game.state.correctAnswers);
//    var xhttp = new XMLHttpRequest();
//    xhttp.onreadystatechange = function() {
//       console.log("hi2");
//       if (this.readyState == 4 && this.status == 200) {
//        }
//     };
//   document.getElementById("hiddenScore").value = game.state.correctAnswers;
//   var fscore = document.getElementById("hiddenScore").value *10;
//   console.log(fscore);
//   xhttp.open("POST","quiz?command=quizTimeInsert&fscore="+fscore,true);
//   xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
//   xhttp.send();
    game.state.questionsView.fadeOut(100, function() {
      game.state.gameEndText[0].innerHTML = endText;
      game.state.gameEndView.fadeIn(200);
    });
  }
};

game.init();

var drawGaugeBackground = function() {
  var bg = $("#gauge")[0];
  var ctx = bg.getContext("2d");
  var imd = null;
  var sAngle = Math.PI * 0.75;
  var eAngle = Math.PI * 0.25;

  ctx.beginPath();
  ctx.strokeStyle = "#f4f4f4";
  ctx.lineCap = "round";
  ctx.closePath();
  ctx.fill();
  ctx.lineWidth = 30.0;

  imd = ctx.getImageData(0, 0, 240, 240);

  ctx.putImageData(imd, 0, 0);
  ctx.beginPath();
  ctx.arc(120, 120, 100, sAngle, eAngle, false);
  ctx.stroke();
};

drawGaugeBackground();