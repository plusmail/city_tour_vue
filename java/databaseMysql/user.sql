-- Create users if not exist
CREATE USER IF NOT EXISTS 'final_capstone_owner'@'%' IDENTIFIED BY 'finalcapstone';
CREATE USER IF NOT EXISTS 'final_capstone_appuser'@'%' IDENTIFIED BY 'finalcapstone';

-- Grant permissions
GRANT ALL PRIVILEGES ON *.* TO 'final_capstone_owner'@'%';

GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO 'final_capstone_appuser'@'%';
FLUSH PRIVILEGES;
