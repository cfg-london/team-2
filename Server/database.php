    <?php
    function openConnection()
    {
        //$conn = new mysqli("localhost", "avramian_biabeni", "Acetronic1","avramian_hs");
        //$conn = new mysqli("localhost", "pagespee_biabeniamin", "Acetronic1","pagespee_hard");
        $conn = new mysqli("localhost", "root", "","jp");
        if ($conn->connect_error)
        {
            die("Connection failed: ".$conn->connect_error);
        }
        return $conn;
    }
    //
    function checkUserExists($conn, $username, $password)
    {
        return readOneValue($conn,"select count(*) as value from Users where Username='$username' and Password='$password' ")["value"];
    }
    function checkEmail($conn, $username)
    {
        return readOneValue($conn,"select count(*) as value from Users where Username='$username'")["value"];
    }
    function addNewUser($conn, $name, $contact, $problem, $priorityLevel)
    {
        $sql = "INSERT INTO Users (name, PhoneNumber ) 
        VALUES('$name', '$contact')";
        $result = executeSqlWithoutWarning($conn,$sql);
        if(0 == $result)
        {
            return 0;
        }
        $last_id = $conn->insert_id;
        return addAlert($conn, $last_id, 0, $problem, $priorityLevel);
    }
    function addAlert($conn, $userid, $type, $description, $priorityLevel)
    {
        $sql = "INSERT INTO alert (UserId, Type, Description, PriorityLevel, Time) 
        VALUES($userid, $type, '$description', $priorityLevel, NOW())";
        return executeSqlWithoutWarning($conn,$sql);
    }
    function readAlerts($conn)
    {
        $sql = "SELECT * FROM Users
            inner join alert on alert.UserId = Users.UserId";
        $result = $conn->query($sql);
        $entries=[["Name"=>"","Contact"=>""]];
        for($i=0;$i<$result->num_rows;$i++)
        {
            $row=$result->fetch_assoc();
            $entries[$i]=["Name"=>$row["Name"],
                "Time"=>$row["Time"],
                "Problem"=>$row["Description"]];
        }
        return $entries;
    }
   
    function executeSql($conn,$sql)
    {
        if ($conn->query($sql) === TRUE)
        {
            echo "New record created successfully<br>";
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
    }


    function addEntry($conn,$temperature)
    {
        $sql = "INSERT INTO us (Temperature)
        VALUES ($temperature)";
        echo "<br>".$sql."<br>";
        executeSql($conn,$sql);
        //$last_id = $conn->insert_id;
        //$sql="INSERT INTO phoneentry(EntryId,XLocation,YLocation)
        //VALUES ($last_id,'$xLoc','$yLoc')";
        //echo $sql;
       // executeSql($conn,$sql);
    }
    function executeSqlWithoutWarning($conn,$sql)
    {
        if ($conn->query($sql) === TRUE)
        {
            return 1;
            //echo "New record created successfully<br>";
        } 
        return 0;
    }
    
   
    function readOneValue($conn,$sql)
    {
        $result = $conn->query($sql);
        /*if ($result->num_rows <= 50)
            die("empty table");*/
        $row=$result->fetch_assoc();
       return $row;
    }
    
?>