
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
		GroceryList.forEach(function(item) {
			/*document.getElementById("groceryList").innerHTML
				+= generateGroceryItemHTML(item["itemName"]);*/

			let listItem = document.createElement("li");
			listItem.className = "mdl-list__item";

			let itemNameSpan = document.createElement("span");
			itemNameSpan.className = "mdl-list__item-primary-content";
			/*var textNode = document.createTextNode(item["itemName"]);
			textNode.className = "mdl-list__item-primary-content";
			itemNameSpan.appendChild(textNode);*/
			itemNameSpan.innerHTML = item["itemName"];

			let checkboxSpan = document.createElement("span");
			checkboxSpan.className = "mdl-list__item-secondary-action";
			let checkboxLabel = document.createElement("label");
			checkboxLabel.className = "mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect";
			let checkboxInput = document.createElement("input");
			checkboxInput.type = "checkbox";
			checkboxInput.className = "mdl-checkbox__input";
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
