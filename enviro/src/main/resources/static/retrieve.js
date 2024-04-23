function fetchAll() {
  fetch('http://localhost:8080/api/v1/weather-link/', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json(); // This returns a promise
    })
    .then(data => {
      console.log(data); // Data from the response.json() promise
      populateTable(data);
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

function populateTable(data) {
  const tableBody = document.getElementById('weatherTableBody');
  tableBody.innerHTML = '';

  data.forEach(item => {
    let row = document.createElement('tr');
    row.innerHTML = `
                <td>${item.date}</td>
                <td><button id="${item.date}" onClick=detailsClick(this.id) class="btn btn-primary">Show conditions</button></td>
            `;
    tableBody.appendChild(row);
  });
}

function detailsClick(date){
  window.location.href = 'http://localhost:8080/retrieve/'+date;
  document.getElementById('frameHeading').innerHTML = 'Details'
}

window.onload = function () {
  console.log('fetching');
  fetchAll();
};
