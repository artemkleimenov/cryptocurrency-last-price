Store last price (BTC, ETH, XRP) from cex.io to db and get by endpoints.

/cryptocurrencies/minprice?name=[currency_name] - lowest price of selected cryptocurrency
/cryptocurrencies/maxprice?name=[currency_name] - highest price of selected cryptocurrency
/cryptocurrencies?name=[currency_name]&page=[page_number]&size=[page_size] - return a selected page with selected number of elements, [page_number] and [page_size] are optional if they are missing return default values page = 0, size = 10
/cryptocurrencies/csv - import csv file, report should contain cryptocurrency name, min and max price
