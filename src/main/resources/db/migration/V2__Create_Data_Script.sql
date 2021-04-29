Insert into user(username,balance, password)
values  ("ElonMusk", 1000000,"$2a$10$YC8rOb666mRcFJAna5RDo.Rq2l8ZzvD0G4pRXxhvjF4qaH3nQY3K."),
        ("BrokerBill", 100000,"$2a$10$gyhjbJfMp8XBmjqVkITjyuc/ZjRmeIQZB0lCA9eAeBPPy8mHcQpc6"),
        ("CashKen", 100000,"$2a$10$8PHQ2Eo/GNF/sbsjw5NWD.bvK5P/yWBQSI0ZEBdpf2Mq8nAuDb3j2"),
        ("Ferike", 1000000,"$2a$10$t0HWSvOZZjsN8UNdqKgWbu6BiBQhLvgm8kgV57bpufQpJfKFdE2H6");/*pw: profit*/


Insert into owned_stock(id,amount,buy_in_price,type,user_name)
values  (1,5,2407.62,"GOOG","ElonMusk"),
        (2,2,253.295,"MSFT","Ferike"),
        (3,1,2414.11,"GOOG","BrokerBill"),
        (4,10,66.05,"TWTR","Ferike"),
        (5,32,2500.24,"GOOG","ElonMusk"),
        (6,6,2434.55,"GOOG","Ferike"),
        (7,85,306.55,"FB","Ferike"),
        (8,15,307.11,"FB","CashKen");

Insert into transaction(id,amount,date,stock_price,transaction_type,transaction_value,type,user_name,profit)
values  (1,5,"2021-04-28 03:52",2407.62,"Buy",12038.1,"GOOG","ElonMusk",0),
        (2,2,"2021-04-28 04:04",253.295,"Buy",506.59,"MSFT","Ferike",0),
        (3,1,"2021-04-28 04:20",2414.11,"Buy",2414.11,"GOOG","BrokerBill",0),
        (4,10,"2021-04-28 11:32",66.05,"Buy",660.5,"TWTR","Ferike",0),
        (5,32,"2021-04-28 06:30",2500.24,"Buy",80007.68,"GOOG","ElonMusk",0),
        (6,6,"2021-04-28 10:15",2434.55,"Buy",14607.3,"GOOG","Ferike",0),
        (7,85,"2021-04-28 01:11",306.55,"Buy",26056.75,"FB","Ferike",0),
        (8,15,"2021-04-28 09:59",307.11,"Buy",4606.65,"FB","CashKen",0);



