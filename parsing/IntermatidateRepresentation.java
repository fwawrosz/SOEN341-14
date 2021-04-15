package parsing;

import java.util.ArrayList;
import java.util.List;

public class IntermatidateRepresentation
{
    private List<LineStatements> InterR;

    public IntermatidateRepresentation()
    {
        InterR =  new ArrayList<>();
    }

    public void addNewLineStatement(LineStatements ls)
    {
        InterR.add(ls);
    }

    public List<LineStatements> getIR() {
        return InterR;
    }
}
