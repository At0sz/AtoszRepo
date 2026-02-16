
-- Tábla létrehozása
CREATE TABLE tickets (
                         id INTEGER PRIMARY KEY,
                         device_name TEXT,
                         issue_type TEXT,
                         priority INTEGER, -- 1: Alacsony, 5: Kritikus
                         status TEXT -- 'Open', 'Closed'
);

-- Adatok beszúrása
INSERT INTO tickets (device_name, issue_type, priority, status) VALUES
                 ('Router-01', 'No Link', 5, 'Open'),
                 ('Switch-A2', 'VLAN mismatch', 3, 'Open'),
                 ('Server-North', 'High Latency', 4, 'Closed'),
                 ('Router-02', 'Power loss', 5, 'Open'),
                 ('Access-Point-05', 'SSID missing', 2, 'Open'),                                                    ('Switch-B1', 'Loop detected', 5, 'Closed');