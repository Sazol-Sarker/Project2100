package Library;

class Get_Data_2 {
    
    private int Roll;
    private String Book_name,Writer1_name,Writer2_name,Issued_date;
    
    public Get_Data_2(int Roll,String Book_name,String Writer1_name,String Writer2_name,String Issued_date)
    {
        this.Roll = Roll;
        this.Book_name = Book_name;
        this.Writer1_name = Writer1_name;
        this.Writer2_name = Writer2_name;
        this.Issued_date = Issued_date;
    }

    public int get_Roll()
    {
        return Roll;
    }
    public String get_Book_name()
    {
        return Book_name;
    }
    public String get_Writer1_name()
    {
        return Writer1_name;
    }
    public String get_Writer2_name()
    {
        return Writer2_name;
    }
    public String get_Issued_date()
    {
        return Issued_date;
    }
    
}
