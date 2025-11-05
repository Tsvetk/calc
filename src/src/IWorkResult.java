package src;

import java.io.IOException;
import java.util.List;

interface IWorkResult {
    void write (String file, String data) throws IOException;
    List<String> read (String file) throws IOException;
}
