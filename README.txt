Finance Tracker

This tool is designed to track financial transactions, mainly for tracking personal income and expenses


### To Do List
- Backend
	Required data functionality:
		Basics						api endpoint 		DB call
									in Controller		in Service
			addTrans()				done				done
			getAllTrans()			done				done
			getTransById()			done				*
			updateTrans()			done				*
			deleteTrans()			done				*
		Filtering and Search
			findTrans()				done				done			
			...
		Statistics
			getMonthlyBalance()		done				done
			getBalance()			done				done
			...
		Test & Dummy
			insertDummyData()		done				/
			deleteAllTrans()	
			
- Frontend
	-> Planned to be done with React
	-> Planned after backend is completed
	
	
	
	
### POST request to create a new Transaction for testing:	
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"description": "Payment", "transactionType": "INCOME", "amount": 1200.0, "date": "2025-06-17", "category": "Side Job"}'
  
  
### @RequestParam & @PathVariable
- access to specific ressource 	> @PathVariable	>	/transactions/{id}
- search / filter				> @RequestParam	>	/transactions/filter?id=
		