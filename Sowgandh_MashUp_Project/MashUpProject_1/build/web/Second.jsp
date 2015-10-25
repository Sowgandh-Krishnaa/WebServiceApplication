<!DOCTYPE html>
<html>
<body>
<div style="text-align: center;">
		<div style="box-sizing: border-box; display: inline-block; width: 1000px; height: 910px; max-width: 4800px; background-color: white; border: 2px; box-shadow: 0px 0px 8px blue; margin: 50px auto">
		<div style="background: blue ; border-radius: 5px 5px; padding: 15px;"><span style="font-family: verdana,arial; color: white; font-size: 1.00em; font-weight:bold;">API AND MASHUP SEARCH APPLICATION</span></div>
		<div style="padding: 15px">
		<style type="text/css">
			table.left{margin-left:auto; margin-right:auto;}
		</style> 
 <style>

h2 {
    text-align: left;
}

h3 {
    text-align: right;
} 
</style>  


<div class="vertical-line" style="height: 4235px;" />
<div id="section">
<td><h2>API</h2></td>
<form method="post" action="http://localhost:8080/MashUpProject_1/MashUpServlet" name="aform" target="_top">
    <table class='center'>
			
			<tr><td>Year:</td><td><input type="text" name="Year" value=""></td><td></td></tr>
			<tr><td>Protocol:</td><td><input type="text" name="Protocol" value=""></td></tr>
                        <tr><td>Rating:</td><td><input type="text" name="Rating" ></td></tr>
                        <tr><td>Category:</td><td><input type="text" name="Category"></td></tr>
			<tr><td>Tags:</td><td><input type="text" name="Tags"></td></tr>
			<tr><td>Keywords:</td><td><input type="text" name="Keywords" value=""></td></tr>
            <tr><td><input name="submit1" type="submit" value="Submit"></td></tr>
    </table>
<h2>Mashup</h2>
<table class='center'>
			
			<tr><td>Year:</td><td><input type="text" name="Year2" value=""></td></tr>
			<tr><td>Protocol:</td><td><input type="text" name="Protocol2" value=""></td></tr>
			<tr><td>Rating:</td><td><input type="text" name="Rating2" ></td></tr>
                        <tr><td>Category:</td><td><input type="text" name="Category2"></td></tr>
			<tr><td>Tags:</td><td><input type="text" name="Tags"></td></tr>
			<tr><td>Keywords:</td><td><input type="text" name="Keywords2" value=""></td></tr>
            <tr><td><input name="submit2" type="submit" value="Submit"></td></tr>
    </table>
	<table class='center'>
	<tr><td><textarea id="output" onkeypress="change()" style="float:margin-bottom" rows="22" cols="130" >${data}</textarea></td></tr>
	 <script  type="text/javascript"> 
function change() {
    var a = document.getElementById('output');
    var len = a.value.length;
    a.style.width = 200 + len;
    alert(a.style.width);
}
</script>
        
        </table>
</form>


</div>


</body>
</html>
