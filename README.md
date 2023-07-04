# TisotLib
The library for working with flights in Israel that getting data from govAPI https://data.gov.il/dataset/flydata


ספריה בשביל לעבוד עם נתונים ממאגר טיסות ממשלתי https://data.gov.il/dataset/flydata

TisotLibrary.getHourArrivals is getting Arrivals for hour of system (getHourArrivals(hour, date). To get arrivals before or afted just substract or add an hour on calling the function.
Only need number of hour itselft in format "HH" and date "YYYY-MM-dd". For example

DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH");
DateTimeFormatter date = DateTimeFormatter.ofPattern("YYYY-MM-dd");


WIP. Will add more features soon.
