package org.datdev.job.services.Excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface IExcelService {
    void exportExcel(List<Customer> customers, OutputStream stream) throws IOException;
    void importFromExcel(InputStream inputStream) throws IOException;
}
