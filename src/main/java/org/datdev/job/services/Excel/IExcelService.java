package org.datdev.job.services.Excel;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
@Service
public interface IExcelService<T> {
    void exportExcel(List<T> entities, OutputStream stream) throws IOException;
    List<T> importFromExcel(InputStream inputStream) throws IOException;
}
