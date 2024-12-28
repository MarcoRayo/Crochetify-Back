package utez.edu.mx.crochetifyBack.services.stock;

import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.stock.StockCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.stock.StockUpdateRequest;

public interface StockService {
    ResponseObject createStock(StockCreateRequest request);

    ResponseObject getStockById(Long idStock);

    ResponseList getStocks();

    ResponseObject updateStock(Long id, StockUpdateRequest request);
}