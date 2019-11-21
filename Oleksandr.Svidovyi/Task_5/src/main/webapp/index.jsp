<html>
<head>
    <title>Web Demo</title>
</head>
<body>

<%--<form method="get" action="client">--%>
<%--  <h2>client's ID:</h2><input type="text" id="getClient" name="clientId" />--%>
<%--  <input type="submit" id="getClientButton" value="send get request" />--%>
<%--</form>--%>

<%--<form method="post" action="client">--%>
<%--  <h2>client's ID:</h2>--%>
<%--  <input type="text" id="postId" name="clientId" />--%>
<%--  <h2>client's name:</h2><input type="text" id="postName" name="clientName" />--%>
<%--  <input type="submit" id="postClientButton" value="send post request" />--%>
<%--</form>--%>

<%--<form method="post" action="client">--%>
<%--  <input type="hidden" name="HttpMethod" value="put" />--%>
<%--  <h2>client's ID:</h2>--%>
<%--  <input type="text" id="putId" name="putClientId" />--%>
<%--  <h2>client's name:</h2><input type="text" id="putName" name="newClientName" />--%>
<%--  <input type="submit" id="putClientButton" value="send put request" />--%>
<%--</form>--%>

<%--<script>--%>
<%--  $("#putClient").submit(function(event){--%>
<%--    event.preventDefault();--%>
<%--    var $form = $(putClient);--%>
<%--    var putClientId = $form.find('input[name="putClientId"]').val();--%>
<%--    var url = 'http://localhost:8099/com.hillel.task_5/client';--%>
<%--    var newClientName = $form.find('input[name="newClientName"]').val();--%>

<%--    $.ajax({--%>
<%--      type : 'PUT',--%>
<%--      url : url,--%>
<%--      contentType: 'application/json',--%>
<%--      data : JSON.stringify({putId: putClientId, putName: newClientName}),--%>
<%--      success : function(data, status, xhr){--%>
<%--        window.location.replace('http://localhost:8099/com.hillel.task_5/client'+putClientId);--%>
<%--      },--%>
<%--      error: function(xhr, status, error){--%>
<%--        $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')--%>
<%--      }--%>
<%--    });--%>
<%--  });--%>

<%--</script>--%>

</body>
</html>