"use strict";

// Dar de alta un estudiante
let formClient = document.getElementById("addClient");
if (formClient != null) {
    formClient.addEventListener("submit", addClient);
}

function addClient() {
    let client = {
        name: document.getElementById("nombre").value,
        lastname: document.getElementById("apellido").value,
    }
    fetch('clients/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(client)
    })
        .then(response => {
            getClients();
        })
        .catch(error => console.log(error));
}


function getClients() {
    let table = document.getElementById("datos");
    let rowCount = table.rows.length; //la cantidad de filas de la tabla generada, con id="datos"

    for (let i = 1; i < rowCount; i++) {
        table.deleteRow(rowCount - i);
    }

    fetch('clients/')
        .then(response => response.json())
        .then(resp => {
            for (const elem of resp) {
                agregar(elem.id, elem.name, elem.lastname);
            }
        })
        .catch(error => console.log(error));

}

//Agrego los datos a la tabla

function agregar(id, name, lastname) {
    let tBody = document.getElementById("menu_tabla");

    let tR = document.createElement("tr");


    let tD1 = document.createElement("td");
    let tD2 = document.createElement("td");
    let tD3 = document.createElement("td");

    let tD4 = document.createElement("button");
    tD4.innerHTML = "Eliminar"
    tD4.dataset.id = id;
    tD4.addEventListener("click", eliminarPurchase);

    let tD5 = document.createElement("button");
    tD5.innerHTML = "Modificar"
    tD5.dataset.id = id;
    tD5.setAttribute("class", "edit");
    //tD5.addEventListener("click", modificarDatos);

    let tD6 = document.createElement("button");
    tD6.innerHTML = "Guardar";
    tD6.dataset.id = id;
    tD6.setAttribute("class", "save");



    let nodo1 = document.createTextNode(id);
    let nodo2 = document.createTextNode(name);
    let nodo3 = document.createTextNode(lastname);

    tD1.appendChild(nodo1);
    tD2.appendChild(nodo2);
    tD3.appendChild(nodo3);
    tR.appendChild(tD1);
    tR.appendChild(tD2);
    tR.appendChild(tD3);
    tR.appendChild(tD4);
    tR.appendChild(tD5);
    tR.appendChild(tD6);
    tBody.appendChild(tR);
};

getClients();

//Funcion para delete de cliente

function eliminarPurchase(event) {
    let id = event.target.dataset.id;
    let cont = 0;

    fetch('purchases/')
        .then(response => response.json())
        .then(resp => {
            for (const elem of resp) {
                if (elem.client.id == id) {
                    cont = cont + 1;
                    console.log(cont);
                    probando(elem.id, elem.client.id, cont);
                }
            }
        })
        .catch(error => console.log(error));

    if (cont == 0) {
        console.log("Entro");
        prueba2(id);
    }
}

function probando(id, client_id, cont) {
    fetch('purchases/' + id, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
    })
        .then(response => {
            console.log("purchase");
            console.log(response);
            if (cont < 2) {
                eliminarClient(client_id);
            }
        })
        .catch(error => console.log(error));
}

function eliminarClient(id) {

    fetch('clients/')
        .then(response => response.json())
        .then(resp => {
            for (const elem of resp) {
                if (elem.id == id) {
                    prueba2(elem.id);
                }
            }
        })
        .catch(error => console.log(error));

}

function prueba2(id) {

    fetch('clients/' + id, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
    })
        .then(response => {
            console.log("Se borro de la base");
            getClients();
        })
        .catch(error => console.log(error));
}

//Fin funciones para delete de cliente

