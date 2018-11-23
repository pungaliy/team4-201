var roomID;
var currentUserID;
var currentName;

var socket;

function connectSocket(){
	socket = new WebSocket("ws://localhost:8080/sockets/tabs");
	socket.onopen = function (event){
		console.log("JS TabsSocket connected.");
	};
	socket.onmessage = function (event){
		console.log("Need to update list...");
		loadGroceryList();
		loadTransactionList();
		loadTabsTotalList();
		console.log("All list up to date");
	};
	socket.onclose = function (event){
		console.log("JS TabsSocket closed.");
		//connectSocket();
		//console.log("JS TabsSocket reconnect");
	};
	socket.onerror = function (event){
		console.log("JS TabsSocket error.", event);
	};
}

function broadcastUpdate(){
	console.log("Try to braodcast update...");
	socket.send("");
}



function loadUserObjAndRoom(){
	console.log("Try to open ajax");
	$.ajax({
		url: "/get-user",
		method: "POST",
		data: {},
		dataType: "json",
		success: function (response) {
			console.log("Sucessfully loaded user and room", response);
			let userObj = response;
			console.log(userObj);
			currentUserID = userObj["userID"];
			currentName = userObj["fullName"];
			roomID = userObj["roomID"];
			console.log("Loaded with these param:", currentUserID, currentName, roomID);
			loadGroceryList();
			loadTransactionList();
			loadTabsTotalList();
			loadRoommatesForAddTransaction();
		},
		error: function(response){
			console.log("Error loading userobj", response);
		}
	});
}

function loadRoommatesForAddTransaction(){
	let transactionOpt = document.getElementById("addTransactionOptions");
	let newList = document.getElementById("roommateList");
	newList.innerHTML = "";
	let newPurchaserList = document.getElementById("purchaserList");
	newList.innerHTML = "";
	let roommateList = [];

	$.ajax({
		url: "/GetRoommates",
		method: "GET",
		data: {},
		dataType: "json",
		success: function (response) {
			console.log("Sucessfully loaded roommates", response);
			roommateList = response;
			//Splitters
			let listnamesplit = document.createElement("textNode");
			listnamesplit.innerHTML = "Splitters: ";
			transactionOpt.appendChild(listnamesplit);
			roommateList.forEach(function(roommate){
				let name = document.createElement("textNode");
				name.innerHTML = roommate["fullName"];
				let checkbox = document.createElement("input");
				checkbox.type = "checkbox";
				checkbox.value = roommate["userID"];
				newList.appendChild(name);
				newList.appendChild(checkbox);
			});
			transactionOpt.appendChild(newList);

			//Purchaser
			let listnamebuy = document.createElement("textNode");
			listnamebuy.innerHTML = "Purchaser: ";
			transactionOpt.appendChild(listnamebuy);
			roommateList.forEach(function(roommate){
				let name = document.createElement("textNode");
				name.innerHTML = roommate["fullName"];
				let radio = document.createElement("input");
				radio.type = "radio";
				radio.value = roommate["userID"];
				newPurchaserList.appendChild(name);
				newPurchaserList.appendChild(radio);
			});
			transactionOpt.appendChild(newPurchaserList);
		},
		error: function(response){
			console.log("Error loading roommates", response);
		}
	});


}


function loadGroceryList(){
	$.ajax({
		url: "/GroceryList",
		method: "GET",
		data: {},
		dataType: "json",
		success: function (response) {
			console.log("Sucessfully loaded grocery", response);
			let GroceryList = response;
			let list = document.getElementById("groceryList");

			//Clear the list
			list.innerHTML = "";
			GroceryList.forEach(function(item) {
				let listItem = document.createElement("li");
				listItem.className = "mdl-list__item";
				listItem.id = item["itemName"]+item["roomID"];

				let itemNameSpan = document.createElement("span");
				itemNameSpan.className = "mdl-list__item-primary-content";
				itemNameSpan.innerHTML = item["itemName"];

				let checkboxSpan = document.createElement("span");
				checkboxSpan.className = "mdl-list__item-secondary-action";
				let checkboxLabel = document.createElement("label");
				checkboxLabel.className = "mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect";
				let checkboxInput = document.createElement("input");
				checkboxInput.type = "checkbox";
				checkboxInput.className = "mdl-checkbox__input";
				checkboxInput.id = item["itemName"];
				checkboxInput.onclick = function(){
					deleteGroceryClick("5566", item["itemName"]);
				};
				checkboxLabel.appendChild(checkboxInput);
				checkboxSpan.appendChild(checkboxLabel);

				listItem.appendChild(itemNameSpan);
				listItem.appendChild(checkboxSpan);

				list.appendChild(listItem);
			});
			componentHandler.upgradeDom();
		},
		error: function(response){
			console.log("Error loading groceries", response);
		}
	});
}

function addGroceryClick(){
	let name = document.addGroceryOptions.itemNameInput.value;
	addGroceryPass(roomID, name, "Y");
	return false;
}

function addGroceryPass(roomid, itemname, addYN){
	let toPass = {};
	toPass["roomID"] = roomid;
	toPass["itemName"] = itemname;
	toPass["add"] = addYN;
	let param = toPass;
	$.ajax({
		type: "POST",
		url: "/GroceryList",
		data:JSON.stringify(param),
		contentType: "application/json",
		success: function(status){
			console.log("Grocery Sent", param);
			broadcastUpdate();
			loadGroceryList();
			connectSocket();
		},
		error:function(error){
			console.log("Error sending grocery item",error);
			broadcastUpdate();
			loadGroceryList();
			connectSocket();
		}
	});
}

function deleteGroceryClick(roomID, itemName){
	deleteGroceryPass(roomID, itemName, "N");
}

function deleteGroceryPass(roomid, itemname, addYN){
	let toPass = {};
	toPass["roomID"] = roomid;
	toPass["itemName"] = itemname;
	toPass["add"] = addYN;
	//let param = JSON.stringify(toPass);
	let param = toPass;
	$.ajax({
		type: "POST",
		url: "/GroceryList",
		data:JSON.stringify(param),
		contentType: "application/json",
		success: function(status){
			console.log("Grocery delete",status);
			broadcastUpdate();
			loadGroceryList();
			connectSocket();
		},
		error:function(error){
			console.log("Error removing grocery item",error);
			broadcastUpdate();
			loadGroceryList();
			connectSocket();
		}
	});
}

function loadTransactionList(){
	$.ajax({
		url: "/TransactionList",
		method: "GET",
		data: {},
		dataType: "json",
		success: function (response) {
			console.log("Sucessfully loaded transactions", response);
			let TransactionList = response;
			let table = document.getElementById("transactionList");
			table.className = "mdl-data-table mdl-js-data-table mdl-shadow--2dp";
			table.innerHTML = "";
			let tHead = document.createElement("thead");
			let headrow = document.createElement("tr");
			let itemHead = document.createElement("th");
			itemHead.className = "mdl-data-table__cell--non-numeric";
			itemHead.innerHTML = "Product";
			let priceHead = document.createElement("th");
			priceHead.innerHTML = "Total price";
			let splitHead = document.createElement("th");
			splitHead.innerHTML = "Split";
			let purchaserHead = document.createElement("th");
			purchaserHead.innerHTML = "Purchaser";
			headrow.appendChild(itemHead);
			headrow.appendChild(priceHead);
			headrow.appendChild(splitHead);
			headrow.appendChild(purchaserHead);
			tHead.appendChild(headrow);
			table.appendChild(tHead);

			let tBody = document.createElement("tbody");
			table.appendChild(tBody);

			TransactionList.forEach(function(item) {
				let row = document.createElement("tr");
				let tdItemName = document.createElement("td");
				tdItemName.className = "mdl-data-table__cell--non-numeric";
				tdItemName.innerHTML = item["item"];
				let tdAmount = document.createElement("td");
				let tdSplit = document.createElement("td");
				let tdPurchaser = document.createElement("td");
				/*if(item["user1"] === currentName){
					tdAmount.innerHTML = item["amount"];
					tdSplit.innerHTML = item["user2"];
				} else {
					tdAmount.innerHTML = "-";
					tdAmount.innerHTML += item["amount"];
					tdSplit.innerHTML = item["user1"];
				}*/
				tdAmount.innerHTML = item["amount"];
				tdSplit.innerHTML = item["user2"];
				tdPurchaser.innerHTML = item["user1"];
				row.appendChild(tdItemName);
				row.appendChild(tdAmount);
				row.appendChild(tdSplit);
				row.appendChild(tdPurchaser);

				tBody.appendChild(row);

			});
			componentHandler.upgradeDom();
		},
		error: function(response){
			console.log("Error loading transactions", response);
		}
	});
}

function addTransactionClick(){
	let itemname = document.addTransactionOptions.newTransactionItemName.value;
	let quantity = document.addTransactionOptions.newTransactionQuantity.value;
	let pricePerItem = document.addTransactionOptions.newTransactionPricePerItem.value;
	let splitters = [];
	let purchaser;
	let allInput = document.getElementById("addTransactionOptions").getElementsByTagName("input");
	for(let i = 0; i < allInput.length; i++){
		if(allInput[i].type == "checkbox" && allInput[i].checked == true){
			splitters.push(allInput[i].value);
		} else if (allInput[i].type == "radio" && allInput[i].checked == true){
			purchaser = allInput[i].value;
		}
	}
	addTransactionPass(roomID, itemname, quantity, pricePerItem, purchaser, splitters);
	return false;

}

function addTransactionPass(roomid, itemname, q, ppi, buy, split){
	let toPass = {};
	toPass["roomID"] = roomid;
	toPass["itemName"] = itemname;
	toPass["quantity"] = q;
	toPass["pricePerItem"] = ppi;
	toPass["purchaser"] = buy;
	toPass["splitters"] = split;
	let param = toPass;
	console.log("Try to send", param);
	$.ajax({
		type: "POST",
		url: "/TransactionList",
		data:JSON.stringify(param),
		contentType: "application/json",
		success: function(status){
			broadcastUpdate();
			loadTransactionList();
			loadTabsTotalList();
			connectSocket();
			console.log("Transactions Sent",status);
		},
		error:function(error){
			broadcastUpdate();
			loadTransactionList();
			loadTabsTotalList();
			connectSocket();
			console.log("Error Transactions",error);
		}
	});
}

function loadTabsTotalList(){
	$.ajax({
		url: "/TabsTotalList",
		method: "GET",
		data: {},
		dataType: "json",
		success: function (response) {
			console.log("Sucessfully loaded tabs", response);
			let tabsTotalList = response;
			let list = document.getElementById("tabsList");
			list.innerHTML = "";
			tabsTotalList.forEach(function(item){
				//user1 is always current user
				//list.innerHTML += (item["user2"] + item["amount"]);
				let listItem = document.createElement("li");
				listItem.className = "mdl-list__item mdl-list__item--two-line";
				let span1 = document.createElement("span");
				span1.className = "mdl-list__item-primary-content";
				let i = document.createElement("i");
				i.className = "material-icons mdl-list__item-avatar";

				let nameSpan = document.createElement("span");
				nameSpan.innerHTML = item["user2"];
				let amountSpan = document.createElement("span");
				amountSpan.className = "mdl-list__item-sub-title";
				if(parseFloat(item["amount"]) < 0){
					let a = item["amount"];
					a *= -1;
					amountSpan.innerHTML = ( "-$" + a );
				} else {
					amountSpan.innerHTML = ("$" + item["amount"]);
				}

				span1.appendChild(i);
				span1.appendChild(nameSpan);
				span1.appendChild(amountSpan);
				listItem.appendChild(span1);
				list.appendChild(listItem);
			});
			componentHandler.upgradeDom();
		},
		error: function(response){
			console.log("Error loading tabs", response);
		}
	});
}
