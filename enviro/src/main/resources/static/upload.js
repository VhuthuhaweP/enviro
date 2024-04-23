function uploadFile(){
        const input = document.getElementById('fileUpload');
        const file = input.files[0];
        if (file) {
            const formData = new FormData();
            formData.append('file', file);

            fetch('/api/v1/documents/upload', {
                method: 'POST',
                body: formData,
            })
            .then(response => response.text())
            .then(data => {
                window.location.href = 'http://localhost:8080/retrieve';
            })
            .catch(error => {
                alert('Error uploading file: ' + error.message);
            });
        } else {
            alert('Please select a file.');
        }
}

