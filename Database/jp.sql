-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 18, 2017 at 12:40 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jp`
--

-- --------------------------------------------------------

--
-- Table structure for table `alert`
--

CREATE TABLE `alert` (
  `AlertId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Type` int(11) NOT NULL,
  `Description` text NOT NULL,
  `PriorityLevel` int(11) NOT NULL,
  `Time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alert`
--

INSERT INTO `alert` (`AlertId`, `UserId`, `Type`, `Description`, `PriorityLevel`, `Time`) VALUES
(1, 2, 0, 'FALL', 9, '2017-11-18 12:22:05'),
(2, 3, 0, 'FALL', 9, '2017-11-18 12:22:42'),
(3, 4, 0, 'FALL', 9, '2017-11-18 12:22:52'),
(4, 5, 0, 'Home Alert', 1, '2017-11-18 12:32:26'),
(5, 5, 0, 'Food Alert', 1, '2017-11-18 12:32:26'),
(6, 5, 0, 'Money Alert', 1, '2017-11-18 12:32:26'),
(7, 5, 0, 'Life Alert', 1, '2017-11-18 12:32:26'),
(8, 6, 0, 'FALL', 9, '2017-11-18 12:41:32'),
(9, 7, 0, 'FALL', 9, '2017-11-18 12:41:37'),
(10, 8, 0, 'FALL', 9, '2017-11-18 12:41:43'),
(11, 9, 0, 'FALL', 9, '2017-11-18 12:41:48'),
(12, 10, 0, 'FALL', 9, '2017-11-18 12:42:00'),
(13, 11, 0, 'FALL', 9, '2017-11-18 12:42:05'),
(14, 12, 0, 'FALL', 9, '2017-11-18 12:42:14'),
(15, 13, 0, 'FALL', 9, '2017-11-18 12:42:19'),
(16, 14, 0, 'FALL', 9, '2017-11-18 12:42:47'),
(17, 15, 0, 'FALL', 9, '2017-11-18 12:42:52'),
(18, 16, 0, 'FALL', 9, '2017-11-18 12:43:45'),
(19, 17, 0, 'FALL', 9, '2017-11-18 12:45:37'),
(20, 18, 0, 'FALL', 9, '2017-11-18 12:45:48'),
(21, 19, 0, 'FALL', 9, '2017-11-18 12:53:36'),
(22, 20, 0, 'FALL', 9, '2017-11-18 12:54:57'),
(23, 21, 0, 'FALL', 9, '2017-11-18 12:55:03'),
(24, 22, 0, 'FALL', 9, '2017-11-18 12:55:22'),
(25, 23, 0, 'FALL', 9, '2017-11-18 12:55:27'),
(26, 24, 0, 'FALL', 9, '2017-11-18 12:59:13'),
(27, 25, 0, 'FALL', 9, '2017-11-18 13:12:44'),
(28, 26, 0, 'FALL', 9, '2017-11-18 13:12:49'),
(29, 27, 0, 'FALL', 9, '2017-11-18 13:12:55'),
(30, 28, 0, 'FALL', 9, '2017-11-18 13:13:10'),
(31, 29, 0, 'vvv', 9, '2017-11-18 13:13:34'),
(32, 30, 0, 'vvv', 9, '2017-11-18 13:18:03'),
(33, 31, 0, 'FALL', 9, '2017-11-18 13:18:18'),
(34, 32, 0, 'FA', 9, '2017-11-18 13:20:17'),
(35, 33, 0, 'FALL', 9, '2017-11-18 13:20:35'),
(36, 34, 0, 'Health Alert', 1, '2017-11-18 13:38:29'),
(37, 34, 0, 'Home Alert', 2, '2017-11-18 13:38:29'),
(38, 34, 0, 'Food Alert', 1, '2017-11-18 13:38:29'),
(39, 34, 0, 'Money Alert', 1, '2017-11-18 13:38:29');

-- --------------------------------------------------------

--
-- Table structure for table `boardentry`
--

CREATE TABLE `boardentry` (
  `BoardEntryId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Steps` int(11) NOT NULL,
  `Temperature` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `boardentry`
--

INSERT INTO `boardentry` (`BoardEntryId`, `UserId`, `Steps`, `Temperature`) VALUES
(1, 1, 1, 17),
(2, 1, 3, 15),
(3, 1, 9, 18),
(4, 1, 10, 19),
(5, 1, 12, 19),
(6, 1, 13, 18.5),
(7, 1, 16, 19.5),
(8, 1, 16, 17.5),
(9, 1, 16, 17.5),
(10, 1, 17, 18),
(11, 1, 17, 17),
(12, 1, 19, 18),
(13, 1, 0, 19.5),
(14, 1, 2, 19.5),
(15, 1, 3, 19.5),
(16, 1, 12, 19.5),
(17, 1, 1, 24),
(18, 1, 1, 24),
(19, 1, 1, 24),
(20, 1, 1, 24),
(21, 1, 1, 24),
(22, 1, 1, 24),
(23, 1, 1, 24),
(24, 1, 1, 24),
(25, 1, 1, 24),
(26, 1, 1, 24),
(27, 1, 1, 23.5),
(28, 1, 1, 24),
(29, 1, 1, 23.5),
(30, 1, 1, 24),
(31, 1, 1, 23.5),
(32, 1, 1, 24),
(33, 1, 1, 24),
(34, 1, 1, 24),
(35, 1, 1, 24),
(36, 1, 1, 24),
(37, 1, 1, 24),
(38, 1, 2, 24.5),
(39, 1, 9, 22.5),
(40, 1, 29, 24.5),
(41, 1, 34, 22),
(42, 1, 37, 23),
(43, 1, 38, 22.5),
(44, 1, 39, 22),
(45, 1, 39, 22),
(46, 1, 40, 22.5),
(47, 1, 40, 22.5),
(48, 1, 41, 22.5),
(49, 1, 12, 23),
(50, 1, 39, 22.5),
(51, 1, 54, 23),
(52, 1, 59, 23),
(53, 1, 67, 22.5),
(54, 1, 80, 23);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `LocationId` int(11) NOT NULL,
  `AlertId` int(11) NOT NULL,
  `LocX` double NOT NULL,
  `LocY` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`LocationId`, `AlertId`, `LocX`, `LocY`) VALUES
(1, 1, 0, 0),
(2, 2, 0, 0),
(3, 3, 0, 0),
(4, 4, 51.5028668, -0.0211781),
(5, 5, 51.5028668, -0.0211781),
(6, 6, 51.5028668, -0.0211781),
(7, 7, 51.5028668, -0.0211781),
(8, 8, 0, 0),
(9, 9, 0, 0),
(10, 10, 0, 0),
(11, 11, 0, 0),
(12, 12, 0, 0),
(13, 13, 0, 0),
(14, 14, 0, 0),
(15, 15, 0, 0),
(16, 16, 0, 0),
(17, 17, 0, 0),
(18, 18, 0, 0),
(19, 19, 0, 0),
(20, 20, 0, 0),
(21, 21, 51.507332, -0.011203),
(22, 22, 51.507332, -0.011203),
(23, 23, 51.507332, -0.011203),
(24, 24, 51.507332, -0.011203),
(25, 25, 51.507332, -0.011203),
(26, 26, 51.507332, -0.011203),
(27, 27, 0, 0),
(28, 28, 0, 0),
(29, 29, 0, 0),
(30, 30, 0, 0),
(31, 31, 0, 0),
(32, 32, 0, 0),
(33, 33, 0, 0),
(34, 34, 0, 0),
(35, 35, 0, 0),
(36, 36, 51.5028994, -0.0211226),
(37, 37, 51.5028994, -0.0211226),
(38, 38, 51.5028994, -0.0211226),
(39, 39, 51.5028994, -0.0211226);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserId` int(11) NOT NULL,
  `Name` varchar(60) NOT NULL,
  `PhoneNumber` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserId`, `Name`, `PhoneNumber`) VALUES
(1, 'Ion', '0734523122'),
(2, 'Ion', '0734523122'),
(3, 'Ion', '0734523122'),
(4, 'Ion', '0734523122'),
(5, 'cosmin', 'hdkfb'),
(6, 'Ion', '0734523122'),
(7, 'Ion', '0734523122'),
(8, 'Ion', '0734523122'),
(9, 'Ion', '0734523122'),
(10, 'Ion', '0734523122'),
(11, 'Ion', '0734523122'),
(12, 'Ion', '0734523122'),
(13, 'Ion', '0734523122'),
(14, 'Ion', '0734523122'),
(15, 'Ion', '0734523122'),
(16, 'Ion', '0734523122'),
(17, 'Ion', '0734523122'),
(18, 'Ion', '0734523122'),
(19, 'Ion', '0734523122'),
(20, 'Ion', '0734523122'),
(21, 'Ion', '0734523122'),
(22, 'Ion', '0734523122'),
(23, 'Ion', '0734523122'),
(24, 'Ion', '0734523122'),
(25, 'Ion', '0734523122'),
(26, 'Ion', '0734523122'),
(27, 'Ion', '0734523122'),
(28, 'Ion', '0734523122'),
(29, 'Ion', '0734523122'),
(30, 'Ion', '0734523122'),
(31, 'Ion', '0734523122'),
(32, 'Ion', '0734523122'),
(33, 'Ion', '0734523122'),
(34, 'John Smitt', '0745385379');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alert`
--
ALTER TABLE `alert`
  ADD PRIMARY KEY (`AlertId`);

--
-- Indexes for table `boardentry`
--
ALTER TABLE `boardentry`
  ADD PRIMARY KEY (`BoardEntryId`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`LocationId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alert`
--
ALTER TABLE `alert`
  MODIFY `AlertId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `boardentry`
--
ALTER TABLE `boardentry`
  MODIFY `BoardEntryId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `LocationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
