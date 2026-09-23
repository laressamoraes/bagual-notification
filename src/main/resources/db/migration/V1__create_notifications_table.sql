CREATE TABLE notifications (
	notification_id		UUID PRIMARY KEY,
	transaction_id		UUID NOT NULL,
	message				VARCHAR(500) NOT NULL,
	channel				VARCHAR(50) NOT NULL
);