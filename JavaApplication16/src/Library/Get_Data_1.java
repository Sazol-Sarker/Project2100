package Library;

class Get_Data_1 {
    
    private String Book_name,Writer1_name,Writer2_name;
    private int Amount,Issued;
    
    public Get_Data_1(String Book_name,String Writer1_name,String Writer2_name,int Amount,int Issued)
    {
        this.Book_name = Book_name;
        this.Writer1_name = Writer1_name;
        this.Writer2_name = Writer2_name;
        this.Amount = Amount;
        this.Issued = Issued;
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
    public int get_Amount()
    {
        return Amount;
    }
    public int get_Issued()
    {
        return Issued;
    }
}
