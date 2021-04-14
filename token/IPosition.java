package token;
// IPosition.java - (c) 2001-2021 by Michel de Champlain

public interface IPosition {
    int    getLineNumber();
    int    getColumnNumber();
    void   incLineNumber();
    void   incColumnNumber();
    void   resetLineNumber();
    void   resetColumnNumber();
}
