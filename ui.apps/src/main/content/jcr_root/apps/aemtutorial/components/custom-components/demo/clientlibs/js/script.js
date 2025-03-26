document.getElementById("btn").addEventListener("click", function() {
    const btn = document.getElementById("btn");
  btn.style.color= "black";
    btn.style.background= "yellow"

});

const buttons = document.querySelectorAll(".multicardclick");

// Loop through each button and add a click event
buttons.forEach(button => {
    button.addEventListener("click", function() {
      this.style.color= "black";
    this.style.background= "yellow"
    });
});