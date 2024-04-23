function handleNavigation(path) {

    console.log(path);

    document.getElementById('renderApplication').src = "/" + path;

    document.getElementById('frameHeading').innerHTML = path == 'retrieve' ? 'Retrieve' : path == 'upload' ? 'Upload' : path + ''.startsWith("Details") ? 'Detailed' : 'Home';

}