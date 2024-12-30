class Answer {
    static adjustFontSize() {
        const answerCircles = document.querySelectorAll(".answer-circle");
        
        answerCircles.forEach((circle, index) => {
            const text = circle.querySelector(".answer-text");
            if (text) {
                let fontSize = 16;
                text.style.fontSize = `${fontSize}px`;

                while (text.scrollHeight > circle.clientHeight && fontSize > 8) {
                    fontSize--;
                    text.style.fontSize = `${fontSize}px`;
                }
            }
        });
    }
}

window.Answer = Answer;
