
/*function generateGroceryItemHTML(itemName){
	let head = '<li className="mdl-list__item"><span className="mdl-list__item-primary-content">';
	let end =
	'</span><span class="mdl-list__item-secondary-action"><label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect"><input type="checkbox" class="mdl-checkbox__input"  /></label></span></li>';
	return head+itemName+end;
}*/

function loadGroceryList(){
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "/GroceryList", true);
	xhttp.onreadystatechange = function () {
		let GroceryList = JSON.parse(this.responseText);
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
			checkboxInput.onclick = function(){
				deleteGroceryClick();
			};
			checkboxLabel.appendChild(checkboxInput);
			checkboxSpan.appendChild(checkboxLabel)

			listItem.appendChild(itemNameSpan);
			listItem.appendChild(checkboxSpan);

			componentHandler.upgradeElement(listItem);
			list.appendChild(listItem);
			componentHandler.upgradeElement(list);
		});
		componentHandler.upgradeDom();
	};
	xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhttp.send();
}

function addGroceryClick(){
	addGroceryPass();

}

function addGroceryPass(){
	let param = {roomID:"5566", itemName:"fish", add:"Y"};
	$.ajax({
		type: "POST",
		url: "/GroceryList",
		data:JSON.stringify(param),
		success: function(status){
			loadGroceryList();
			console.log("Grocery Sent",status);
		},
		error:function(error){
			console.log("Error sending grocery item",error);
		}
	});
}

function deleteGroceryClick(){
	deleteGroceryPass();
}

function deleteGroceryPass(){
	let param = {roomID:"5566", itemName:"Beef", add:"N"};
	$.ajax({
		type: "POST",
		url: "/GroceryList",
		data:JSON.stringify(param),
		success: function(status){
			loadGroceryList();
			console.log("Grocery delete",status);
		},
		error:function(error){
			console.log("Error removing grocery item",error);
		}
	});
}

function loadTransactionList(){
	alert("HI I am loading the Transaction List");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
	var TransactionList = [];
	alert("try to parse json...")
	TransactionList = JSON.parse(this.responseText);
	document.getElementById("removethis").innerHTML = "";
	TransactionList.forEach(function(item) {
	document.getElementById("removethis").innerHTML
	+= generateGroceryItemHTML(item["item"] + " " + item["amount"] + " " +
	item["user2"]);

});
};
	//xhttp.setRequestHeader(“Content-Type”, “application/x-www-form-urlencoded”);
	xhttp.open("GET", "/TransactionList", true);
	xhttp.send();
}
