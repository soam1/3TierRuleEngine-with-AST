document.getElementById('rule-form').addEventListener('submit', function(event) {
    event.preventDefault();
    const rule = document.getElementById('rule').value;

    fetch('/api/create_rule', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'rule': rule
        })
    }).then(response => response.json())
      .then(data => console.log(data));
});

document.getElementById('evaluation-form').addEventListener('submit', function(event) {
    event.preventDefault();
    const userData = document.getElementById('user-data').value;
    const ast = ... // Retrieve the AST from the backend or have it stored in the frontend

    fetch('/api/evaluate_rule', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'userData': userData,
            'ast': ast
        })
    }).then(response => response.json())
      .then(data => console.log(data));
});
