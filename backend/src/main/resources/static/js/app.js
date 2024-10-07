const BASE_URL = 'http://localhost:8080/api'; // Adjust the port if necessary

// Function to create a new account
document.getElementById('createAccountForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const name = document.getElementById('accountName').value;
    const initialBalance = document.getElementById('initialBalance').value;

    fetch(`${BASE_URL}/createaccount`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, initialBalance: parseFloat(initialBalance) }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        alert('Account created: ' + JSON.stringify(data));
        document.getElementById('createAccountForm').reset();
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Failed to create account: ' + error.message);
    });
});

// Function to transfer funds
document.getElementById('transferFundsForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const fromAccountID = document.getElementById('fromAccountID').value;
    const toAccountID = document.getElementById('toAccountID').value;
    const amount = document.getElementById('transferAmount').value;

    fetch(`${BASE_URL}/transferfunds`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            fromAccountID: parseInt(fromAccountID),
            toAccountID: parseInt(toAccountID),
            amount: parseFloat(amount)
        }),
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(errData => {
                throw new Error(errData.error || errData.message || 'Transfer failed');
            });
        }
        return response.json(); // Parse JSON if response is ok
    })
    .then(data => {
        alert(data.message); // Show success message
        document.getElementById('transferFundsForm').reset();
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Failed to transfer funds: ' + error.message);
    });
});


// Function to load transaction history
document.getElementById('loadHistoryBtn').addEventListener('click', function() {
    const accountID = prompt("Please enter the Account ID to load its transaction history:");
    
    if (accountID) {
        fetch(`${BASE_URL}/${accountID}/history`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            const transactionHistory = document.getElementById('transactionHistory');
            transactionHistory.innerHTML = ''; // Clear previous entries
            if (data.length === 0) {
                transactionHistory.innerHTML = '<li class="list-group-item">No transactions found.</li>';
            } else {
                data.forEach(transaction => {
                    const li = document.createElement('li');
                    li.className = 'list-group-item';
                    li.textContent = `From: ${transaction.fromAccountID}, To: ${transaction.toAccountID}, Amount: ${transaction.amount}, Current Balance: ${transaction.currentBalance}, Date: ${transaction.timestamp}`;
                    transactionHistory.appendChild(li);
                });
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Failed to load transaction history: ' + error.message);
        });
    }
});