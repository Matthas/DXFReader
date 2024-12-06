package DXFModify;

import DXFRead.DXFDrawing;
import java.util.ArrayList;

//class to add new groups to the DXF file
//its called by ADDGroups
// Arraylist of Strings of DXF Code for groups),
// aryLines = dxf as text,
// object of CAD drawing

public class AddGroups {
    static String[] AddGroups(ArrayList<String> DXFGroups, String[] aryLines, DXFDrawing CAD) {
        int ObjectsEnd = CAD.getObjectsEndIndex()-1;

        if (ObjectsEnd >= 0 && ObjectsEnd < aryLines.length) {
            String[] newLines = new String[aryLines.length + DXFGroups.size()];

            System.arraycopy(aryLines,0,newLines,0,ObjectsEnd +1);

            //insert dXF code for Groups
            for (int i = 0; i < DXFGroups.size(); i++) {
                newLines[ObjectsEnd + 1 + i] = (String) DXFGroups.get(i);
            }
            System.arraycopy(aryLines, ObjectsEnd + 1, newLines, ObjectsEnd + 1 + DXFGroups.size(), aryLines.length - ObjectsEnd - 1 );
            CAD.updateIndexes(ObjectsEnd, DXFGroups.size());
            return newLines;
        } else {
            System.out.println("ADDGroups - out of bounds");
            return aryLines;
        }
    }
}