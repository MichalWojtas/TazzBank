<script>
    const triggerDiv = document.getElementById("togglePasswordChangeDropdown");
    const triggerDiv2 = document.getElementById("toggleLimitsChangeDropdown");
    const item1 = document.getElementById("passwordChangeDropdownMenu");
    const item2 = document.getElementById("limitsChangeDropdownMenu");
    var innerIsOpen = false;
    var innerLimitsIsOpen = false;
    triggerDiv2.addEventListener("click",function (){
        if(!innerLimitsIsOpen){
            item2.style.display = "block";
            innerLimitsIsOpen = true;
        }else{
            item2.style.display = "none";
            innerLimitsIsOpen = false;
        }
    });
    triggerDiv.addEventListener("click", function () {

    if(innerIsOpen === false){
        item1.style.display = "block";
        innerIsOpen = true;
    }else{
        item1.style.display = "none";
        innerIsOpen = false;
    }
    });
</script>