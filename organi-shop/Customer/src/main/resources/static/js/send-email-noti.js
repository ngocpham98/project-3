function Toast (
    id = "",
    type = "",
    title = "",
    message = "",
){
    const toastbox = document.getElementById(id);
    if(toastbox){
        const toastItem = document.createElement("div");
        const icon = "fa-solid fa-circle-check";

        toastItem.classList.add("toasts",`toast--${type}`);
        toastItem.innerHTML = `
                    <div class="toast__icon"><i class= ${icon}></i>
                    </div>
                    <div class="toast__body">
                        <h3 class="toast__title">${title}</h3> 
                        <p class="toast__msg">${message}</p>
                    </div>
        `;
        toastbox.append(toastItem);

        setTimeout(function(){
            toastbox.removeChild(toastItem);
        },3000);
    }
}
// document.getElementById("showSuccessToast").addEventListener("click", function(){
    $("#showSuccessToast").on('submit', function(e) {
        e.preventDefault()
        $.ajax({
            url: $(this).prop('action'),
            type: $(this).prop('method'),
            data: $(this).serialize(),
            success : function() {
                sessionStorage.setItem("reloading", "true");
                document.location.reload();
            }
        });
    });

window.onload = function () {
    let reloading = sessionStorage.getItem("reloading");
    if (reloading) {
        sessionStorage.removeItem("reloading");
        Toast("toast-box",
            "success",
            "Success",
            "You have sent mail successfully"
        );
    }
}




// })