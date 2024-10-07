# Banking Transactions API and Frontend

This project is a banking transactions API built using Java Spring Boot on the backend, and a HTML frontend for interacting with the API. The system handles user accounts, transfers funds between accounts, and retrieves transaction history.

## Features

- **Create a new user account** with an initial balance
- **Transfer funds** between accounts
- **Retrieve transaction history** for a specific account

## Tech Stack

- **Backend**: Java, Spring Boot
- **Frontend**: HTML, CSS, JavaScript

## Requirements

- **Java 17**
- **Maven**

## Getting Started

### Backend Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/karl-zhu9981/banking-service.git
   ```
2. **Run the service**:
   ```bash
   cd banking-service
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```
   The service will be accessible at http://localhost:8080.

## API Endpoints
- **Create Account:** POST /api/createaccount. Payload: account holder name, and initial balance.
- **Transfer Funds:** POST /api/transferfunds. Payload: sending account ID, receiving account ID, and amount of transfer.
- **Get Transaction History:** GET /api/{accountID}/history. Payload: account ID.

## Frontend Features
The frontend provides the following features:

- **Create Account:** Create a new user account by providing a name and an initial balance.
- **Transfer Funds:** Transfer funds between two accounts by specifying the account IDs and the transfer amount.
- **View Account History:** View the account details (including the balance) by entering the account ID.

## Error Handling
The API provides meaningful error responses in case of issues such as:

- Invalid account IDs
- Insufficient funds
- Malformed requests
For each error, the API will return a corresponding HTTP status code and error message in the response body.

## Assumptions Made
- **In-memory storage:** The accounts are stored in memory using a HashMap, and transactions are in an ArrayList.
- All balances are stored as `Double`, and the transfer amounts should not have more than two decimal places.
