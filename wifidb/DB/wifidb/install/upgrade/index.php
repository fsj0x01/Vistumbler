<?php
echo '<title>Wireless DataBase *Alpha*'.$ver["wifidb"].' --> Upgrade Page</title>';
?>
<link rel="stylesheet" href="../../css/site4.0.css">
<body topmargin="10" leftmargin="0" rightmargin="0" bottommargin="10" marginwidth="10" marginheight="10">
<div align="center">
<table border="0" width="75%" cellspacing="10" cellpadding="2">
	<tr>
		<td bgcolor="#315573">
		<p align="center"><b><font size="5" face="Arial" color="#FFFFFF">
		<?php echo 'Wireless DataBase *Alpha* '.$ver["wifidb"].'</font>';?>
		<font color="#FFFFFF" size="2">
            <a class="links" href="/">[Root] </a>/ <a class="links" href="/wifidb/">[WifiDB] </a>/
		</font></b>
		</td>
	</tr>
</table>
</div>
<div align="center">
<table border="0" width="75%" cellspacing="10" cellpadding="2" height="90"><tr>
<td width="17%" bgcolor="#304D80" valign="top">
<!--LINKS-->
</td>

<td width="80%" bgcolor="#A9C6FA" valign="top" align="center">
<!--BODY-->

<form action="patch.php" method="post" enctype="multipart/form-data">
  <h2>WiFiDB Settings for Upgrade</h2>
  <h3>Upgrade DB for 0.15 Build 7x to 0.16 Build 1</h3>
  <h4>Please Read <a target="_blank" href="../notes.html">these notes</a> before installing the Wireless Database</h4>
<?php
$gd = gd_info(); 
if(is_null($gd["GD Version"]))
{
	echo "<h4><font color=#ff0000>You Do Not Have GD or GD2 installed, please install this or you will not beable to use the graphing feature!</font></h4>";
}
else
{ 
	echo "<h4><font color=#00ff00>GD Version: ".$gd['GD Version'].", is installed</font></h4>";
}
?>
<table border="0" cellspacing="0" cellpadding="3">

  <tr>
    <td width="100%">SQL root User (to update the wifidb user priv's)</td><td>........................................</td>
    <td><input name="root_sql_user"></td></tr>
  <tr>
    <td>SQL root user Password</td><td>........................................</td>
    <td><input TYPE=PASSWORD name="root_sql_pwd"></td></tr>
  <tr>
  <td>
    <p>MySQL Host (Default `localhost` )</td><td>........................................</td>
    <td><input name="sqlhost"></td></tr>
  <tr>
    <td>WiFiDB SQL Username</td><td>........................................</td>
    <td><input name="sqlu"></td></tr>
  <tr>
    <td>WiFiDB SQL Password</td><td>........................................</td>
    <td><input name="sqlp"></td></tr>
  <tr>
    <td>WiFi DB name (Default `wifi` )</td><td>........................................</td>
    <td><input name="wifi"></td></tr>
  <tr>
    <td>WiFi Storage DB name (Default `wifi_st` )</td><td>........................................</td>
    <td><input name="wifist"></td>
</TR><TR></TR><TD></TD><TD></TD><TR><TD></TD><TD></TD><TD>
<INPUT TYPE=SUBMIT NAME="submit" VALUE="Submit" STYLE="width: 0.71in; height: 0.36in">
</TD>
</TR>
</TABLE>
</form>
</p>

<?php

$filename = $_SERVER['SCRIPT_FILENAME'];
footer($filename);
?>