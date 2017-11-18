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
    function addNewUser($conn, $name, $contact, $problem, $priorityLevel, $locX, $locY)
    {
        $sql = "INSERT INTO Users (name, PhoneNumber ) 
        VALUES('$name', '$contact')";
        $result = executeSqlWithoutWarning($conn,$sql);
        if(0 == $result)
        {
            return 0;
        }
        $last_id = $conn->insert_id;
        return addAlert($conn, $last_id, 0, $problem, $priorityLevel, $locX, $locY);
    }
    function addNewBoardEntry($conn, $steps, $temperature)
    {
        $sql = "INSERT INTO BoardEntry (UserId, Steps, Temperature ) 
        VALUES(0,'$steps', '$temperature')";
        return  executeSqlWithoutWarning($conn,$sql);
    }
    function addAlert($conn, $userid, $type, $description, $priorityLevel, $locX, $locY)
    {
        $sql = "INSERT INTO alert (UserId, Type, Description, PriorityLevel, Time) 
        VALUES($userid, $type, '$description', $priorityLevel, NOW())";
        $result = executeSqlWithoutWarning($conn,$sql);
        if(0 == $result)
        {
            return 0;
        }
        $last_id = $conn->insert_id;
        return addLocation($conn, $last_id, $locX, $locY);
    }
    function addLocation($conn, $alertId, $locX, $locY)
    {
        $sql = "INSERT INTO Location (AlertId, LocX, LocY) 
        VALUES($alertId,'$locX', '$locY')";
        return executeSqlWithoutWarning($conn,$sql);
    }
    /*function readAlerts($conn)
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
    }*/
    function readPointEntries($conn)
    {
        $sql = "SELECT LocX,LocY,Time FROM Location
            INNER JOIN Alert ON  Alert.AlertId = Location.AlertId
            INNER JOIN Users on Users.UserId = Alert.UserId
            ORDER BY Time DESC LIMIT 100";
        $result = $conn->query($sql);
        /*if ($result->num_rows <= 50)
            die("empty table");*/
        $points=array(array("X"=>0,"Y"=>0));
        for($i=0;$i<$result->num_rows;$i++)
        {
            $row=$result->fetch_assoc();
            $points[$i]["Time"]=$row["Time"];
            $points[$i]["X"]=$row["LocX"];
            $points[$i]["Y"]=$row["LocY"];
        }
        //$points=array_reverse($points);
        //var_dump($points);
        return $points;
    }

    function readAlerts($conn)
    {
        $sql = "SELECT * FROM Location
            INNER JOIN Alert ON  Alert.AlertId = Location.AlertId
            INNER JOIN Users on Users.UserId = Alert.UserId
            ORDER BY Time DESC LIMIT 100";
        $result = $conn->query($sql);
        /*if ($result->num_rows <= 50)
            die("empty table");*/
        $points=array(array("X"=>0,"Y"=>0));
        //echo $result->num_rows;
        for($i=0;$i<$result->num_rows;$i++)
        {
            $row=$result->fetch_assoc();
            $points[$i]["Time"]=$row["Time"];
            $points[$i]["X"]=$row["LocX"];
            $points[$i]["Y"]=$row["LocY"];
            $points[$i]["Name"]=$row["Name"];
            $points[$i]["Contact"]=$row["PhoneNumber"];
            $points[$i]["Y"]=$row["LocY"];
            $points[$i]["Type"]=$row["Type"];
            $points[$i]["Description"]=$row["Description"];
            $points[$i]["PriorityLevel"]=$row["PriorityLevel"];
        }
        //$points=array_reverse($points);
        //var_dump($points);
        return $points;
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