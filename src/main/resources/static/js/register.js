<script>
function togglePassword(inputId, eyeId) {
    let field = document.getElementById(inputId);
    let icon = document.getElementById(eyeId);

    if (field.type === "password") {
        field.type = "text";
        icon.textContent = "🙈";
    } else {
        field.type = "password";
        icon.textContent = "👁️";
    }
}
</script>
