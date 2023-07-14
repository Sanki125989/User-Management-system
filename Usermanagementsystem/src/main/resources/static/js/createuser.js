document.addEventListener("DOMContentLoaded", (event) => {
    const password = document.getElementById("password");        	    
    const passwordAlert = document.getElementById("password-alert");
    const requirements = document.querySelectorAll(".requirements");
    let lengBoolean, bigLetterBoolean, numBoolean, specialCharBoolean;
    let leng = document.querySelector(".leng");
    let bigLetter = document.querySelector(".big-letter");
    let num = document.querySelector(".num");
    let specialChar = document.querySelector(".special-char");
    const specialChars = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?`~";
    const numbers = "0123456789";

    requirements.forEach((element) => element.classList.add("wrong"));

if (password) {
  password.addEventListener("click", () => {
    passwordAlert.classList.remove("d-none");
    if (!password.classList.contains("is-valid")) {
      password.classList.add("is-invalid");
    }
  });
  
      
  password.addEventListener("input", () => {
        let value = password.value;
        if (value.length < 8) {
            lengBoolean = false;
        } else if (value.length > 7) {
            lengBoolean = true;
        }

        if (value.toLowerCase() == value) {
            bigLetterBoolean = false;
        } else {
            bigLetterBoolean = true;
        }

        numBoolean = false;
        for (let i = 0; i < value.length; i++) {
            for (let j = 0; j < numbers.length; j++) {
                if (value[i] == numbers[j]) {
                    numBoolean = true;
                }
            }
        }

        specialCharBoolean = false;
        for (let i = 0; i < value.length; i++) {
            for (let j = 0; j < specialChars.length; j++) {
                if (value[i] == specialChars[j]) {
                    specialCharBoolean = true;
                }
            }
        }

        	        if (lengBoolean == true && bigLetterBoolean == true && numBoolean == true && specialCharBoolean == true) {
        	            password.classList.remove("is-invalid");
        	            password.classList.add("is-valid");

        	            requirements.forEach((element) => {
        	                element.classList.remove("wrong");
        	                element.classList.add("good");
        	            });

        	            passwordAlert.classList.remove("alert-warning");
        	            passwordAlert.classList.add("alert-success");

        	            // If all requirements are met, remove the "alert-success" class after 2000 milliseconds (2 seconds)
        	            setTimeout(() => {
        	                passwordAlert.classList.remove("alert-success");
        	                passwordAlert.remove("alert-success");
        	            }, 2000);
        	        }
 else {
        	            password.classList.remove("is-valid");
        	            password.classList.add("is-invalid");

        	            passwordAlert.classList.add("alert-warning");
        	            passwordAlert.classList.remove("alert-success");

        	            if (lengBoolean == false) {
        	                leng.classList.add("wrong");
        	                leng.classList.remove("good");
        	            } else {
        	                leng.classList.add("good");
        	                leng.classList.remove("wrong");
        	            }

        	            if (bigLetterBoolean == false) {
        	                bigLetter.classList.add("wrong");
        	                bigLetter.classList.remove("good");
        	            } else {
        	                bigLetter.classList.add("good");
        	                bigLetter.classList.remove("wrong");
        	            }

        	            if (numBoolean == false) {
        	                num.classList.add("wrong");
        	                num.classList.remove("good");
        	            } else {
        	                num.classList.add("good");
        	                num.classList.remove("wrong");
        	            }

        	            if (specialCharBoolean == false) {
        	                specialChar.classList.add("wrong");
        	                specialChar.classList.remove("good");
        	            } else {
        	                specialChar.classList.add("good");
        	                specialChar.classList.remove("wrong");
        	            }
        	        }
        	    });
}


    });
    
    function showPassword() {
  const passwordInput = document.getElementById('password');
  const showPasswordCheckbox = document.getElementById('showPasswordCheckbox');
  if (showPasswordCheckbox.checked) {
    passwordInput.type = 'text';
  } else {
    passwordInput.type = 'password';
  }
}


function phonevalidation(input){
	var regex = /[^0-9]/gi;
    input.value = input.value.replace(regex, "");
	    if (input.value.length < 10) {
        input.setCustomValidity("Please enter at least 10 digits");
    } else {
        input.setCustomValidity("");
    }
}

function pincodevalidation(input){
	var regex = /[^0-9]/gi;
    input.value = input.value.replace(regex, "");
	    if (input.value.length < 6) {
        input.setCustomValidity("Please enter 6 digit pincode");
    } else {
        input.setCustomValidity("");
    }
}

                function lettersOnly(input) {
                  var regex = /[^a-z]/gi;
                  input.value = input.value.replace(regex, "");
                }
                



                function FillAddressInput() {
                  let sameAsCurrentCheckbox =
                    document.getElementById("same-as-current");
                  let permanentAddressField = document.getElementById("line");
                  let permanentstreet = document.getElementById("street");
                  let permanentstate = document.getElementById("state");
                  let permanentpincode = document.getElementById("pincode");
                  let permanentcity = document.getElementById("city");

                  let currentAddressField = document.getElementById("line1");
                  let currentstreet = document.getElementById("street1");
                  let currentstate = document.getElementById("state1");
                  let currentpincode = document.getElementById("pincode1");
                  let currentcity = document.getElementById("city1");

                  sameAsCurrentCheckbox.addEventListener("change", () => {
                    if (sameAsCurrentCheckbox.checked) {
                      console.log("coming into 1st");
                      currentAddressField.value = permanentAddressField.value;
                      currentstreet.value = permanentstreet.value;
                      currentstate.value = permanentstate.value;
                      currentpincode.value = permanentpincode.value;
                      currentcity.value = permanentcity.value;
                    } else {
                      currentAddressField.value = "";
                      currentstreet.value = "";
                      currentstate.value = "";
                      currentpincode.value = "";
                      currentcity.value = "";
                    }
                  });

                  permanentAddressField.addEventListener("keyup", () => {
                    if (sameAsCurrentCheckbox.checked) {
                      console.log("coming into 2nd");
                      currentAddressField.value = permanentAddressField.value;
                    }
                  });
                  permanentstreet.addEventListener("keyup", () => {
                    if (sameAsCurrentCheckbox.checked) {
                      console.log("coming into 2nd");
                      currentstreet.value = permanentstreet.value;
                    }
                  });
                  permanentstate.addEventListener("keyup", () => {
                    if (sameAsCurrentCheckbox.checked) {
                      console.log("coming into 2nd");
                      currentstate.value = permanentstate.value;
                    }
                  });
                  permanentpincode.addEventListener("keyup", () => {
                    if (sameAsCurrentCheckbox.checked) {
                      console.log("coming into 2nd");
                      currentpincode.value = permanentpincode.value;
                    }
                  }); 
                  permanentcity.addEventListener("keyup", () => {
                    if (sameAsCurrentCheckbox.checked) {
                      console.log("coming into 2nd");
                      currentcity.value = permanentcity.value;
                    }
                  });
                }
                
                
var firstName = document.getElementById('firstName');
var lastName = document.getElementById('lastName');
var DOB = document.getElementById('date');
var mobileno = document.getElementById('mobileno');

function checkForUpdates() {
  console.log("coming into check values of input fields");
  var currentFirstName = document.getElementById('firstName');
  var currentLastName = document.getElementById('lastName');
  var currentDOB = document.getElementById('date');
  var currentMobileno = document.getElementById('mobileno');

  // Check if any field has been updated
  if (currentFirstName.value !== firstName.value || currentLastName.value !== lastName.value || currentDOB.value !== DOB.value || currentMobileno.value !== mobileno.value) {
    return true; // Allow form to be submitted
  } else {
    alert('Please update form'); // Show popup message
    return false; // Prevent form from being submitted
  }
}



function disappear() {
        	console.log("coming for delete")
          this.style.display = "none";
        }

/*var script = document.createElement("script");
script.src = "https://code.jquery.com/jquery-3.6.0.min.js";
script.src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js";
script.src="https://cdn.datatables.net/1.11.4/js/dataTables.bootstrap4.min.js";
document.getElementsByTagName("head")[0].appendChild(script);*/

$(document).ready(function () {
  $('#dtBasicExample').DataTable({
    "pageLength": 5,
    "lengthMenu": [
      [5,10,15,20,25,-1],
      [5,10,15,20,25,'All']
    ],
  });

  setTimeout(() => {
    const msgDiv = document.querySelector('#messageRemove'); // Get the message div
    if (msgDiv) { // Check if the message div exists
      msgDiv.remove(); // Remove the message div after 2 seconds
    }
  }, 2000);
});

function showAlert(){
	alert("Alert is coming");
}

