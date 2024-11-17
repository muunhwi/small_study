function toggleDropdown(button) {
    const dropdown = button.nextElementSibling.nextElementSibling;
    dropdown.classList.toggle("hidden");
}


function onRejectFormSubmit(e) {
   e.preventDefault();
    if (!confirm("거부 하시겠습니까?")) {
        return;
    }
}

