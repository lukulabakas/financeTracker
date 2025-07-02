Finance Tracker

This tool is designed to track financial transactions, mainly for tracking personal income and expenses


### To Do List

- Backend
	- Setup missing endpoints for statistics data
		-> currently planning functions for filtering/showing statistics

- Frontend
	-> Planned to be done with React
	-> Planned after backend is completed
	
	
	
	
### POST request to create a new Transaction for testing:	
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"description": "Payment", "transactionType": "INCOME", "amount": 1200.0, "date": "2025-06-17", "category": "Subscription"}'
  
  
### @RequestParam & @PathVariable
- access to specific ressource 	> @PathVariable	>	/transactions/{id}
- search / filter				> @RequestParam	>	/transactions/filter?id=
		