<html>
<head>
    <title>Web Demo</title>
</head>
<body>

<form method="get" action="client">
  <h2>client ID:</h2><input type="text" id="clientId" name="clientId" />
  <input type="submit" id="getClientButton" value="get client" />
</form>

<form method="post" action="client">
  <h2>client name:</h2><input type="text" id="postName" name="clientName" />
  <input type="submit" id="postClientButton" value="add client" />
</form>

<form method="get" action="client/account">
  <h2>client ID:</h2><input type="text" id="clientId" name="clientId" />
  <input type="submit" id="getAccountButton" value="get account" />
</form>

</body>
</html>