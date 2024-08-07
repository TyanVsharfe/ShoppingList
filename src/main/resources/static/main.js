(async function(){

    let list = [];

    const response = await fetch("/api/product/all", {
        method: "GET",
        headers: { "Accept": "application/json" }
    });
    if (response.ok === true) {

        list = await response.json();

        for (product of list) {
            append_table_list(product);
        }

        document.getElementById("button_add").addEventListener("click", click_add);
    }

    async function click_add(event) {
        const response = await fetch("api/product/", {
            method: "POST",
            headers: { "Accept": "application/json", "Content-Type": "application/json" },
            body: JSON.stringify({
                name: event.target.form.name.value
            })
        });
    }

    async function click_update(event) {
        const response = await fetch("/api/product/", {
            method: "PUT",
            headers: { "Accept": "application/json", "Content-Type": "application/json" },
            body: JSON.stringify({
                id: event.target.form.id,
                name: event.target.form.input_name.value,
                bought: event.target.form.input_bought.checked,
            })
        });
    }

    async function click_delete(event) {
        const response = await fetch(`/api/product/${event.target.form.id}`,{
            method: "DELETE",
            headers: { "Accept": "application/json" }
        });
    }

    function append_table_list(product) {
        let tr = document.createElement("tr");
        let form = document.createElement("form");
        form.method = "post";
        let products = document.getElementById("products");

        form.id = `${product.id}`;

        tr.innerHTML += `<td><input name="input_name" value="${product.name}" form="${form.id}"></td>`;
        tr.innerHTML += `<td><input name="input_bought" type="checkbox" ${product.bought ? 'checked' : ''} value="${product.bought}" form="${form.id}"></td>`;
        tr.innerHTML += `<td><button type="submit" class="button_update" form="${form.id}">Изменить</button></td>`;
        tr.innerHTML += `<td><button type="submit" class="button_delete" form="${form.id}">Удалить</button></td>`;

        tr.querySelector(".button_update").addEventListener("click",click_update);
        tr.querySelector(".button_delete").addEventListener("click",click_delete);

        products.appendChild(tr);
        document.getElementById("div_main").appendChild(form);
    }
})();