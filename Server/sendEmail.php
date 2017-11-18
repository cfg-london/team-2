<?php
if(isset($_GET["message"]))
{
    mail('beniaminbia@gmail.com',"Alert",$_GET["message"]);
}
?>