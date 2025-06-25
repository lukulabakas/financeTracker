Finance Tracker

This tool is designed to track financial transactions, mainly for tracking personal income and expenses


### To Do List

- Backend
	- Setup User Controller, Service and Repository
	- Required data functionality:
		> TRANSACTION
		Basics						api endpoint 		DB call
									in Controller		in Service
			addTrans()				done				done
			getAllTrans()			done				done
			getTransById()			done				done
			updateTrans()			done				done
			deleteTrans()			done				done
		Filtering and Search
			findTrans()				done				done			
			...
		Statistics
			getMonthlySum()			done				done
			sumAllTransactions()	done				done
			...
		Test & Dummy
			insertDummyData()		done				/
			deleteAllTrans()	
		> CATEGORY
			addCategory()			done				done
			getAllCategories()		done				done
			getCategoryById()		done				done
			updateCategory()		done				done
			deleteCategory()		done				done
		> USER
			addUser()
			getAllUsers()
			getUserById()
			updateUser()
			deleteUser()
	
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
		