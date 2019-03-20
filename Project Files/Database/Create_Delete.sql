--
-- Database: `cleaning`
--
CREATE DATABASE IF NOT EXISTS `cleaning` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cleaning`;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cost`
--

DROP TABLE IF EXISTS `cost`;
CREATE TABLE IF NOT EXISTS `cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cost` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
CREATE TABLE IF NOT EXISTS `jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `service_id` (`service_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
CREATE TABLE IF NOT EXISTS `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
CREATE TABLE IF NOT EXISTS `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(50) NOT NULL,
  `service_desc` varchar(50) NOT NULL,
  `cost_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cost_id` (`cost_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;
