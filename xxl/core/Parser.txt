package xxl.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;

import java.util.Collection;
import java.util.ArrayList;

import xxl.core.exception.UnrecognizedEntryException;

class Parser {

  private Spreadsheet _spreadsheet;
  
  Parser() {
  }

  Parser(Spreadsheet spreadsheet) {
    _spreadsheet = spreadsheet;
  }

  
  Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException /* More Exceptions? */ {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      
      parseDimensions(reader);      // Parses the dimensions of the spreadsheet (first 2 lines of the import file)

      String line;                  // Parses import file lines that contain spreadsheet's entities
      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
    return _spreadsheet;
  }


  private void parseDimensions(BufferedReader reader) {
    int rows = -1;
    int columns = -1;
    
    for (int i = 0; i < 2; i++) {
      
      String line = reader.readLine();
      String[] dimension = line.split("=");
     
      if (dimension[0].equals("linhas"))
        rows = Integer.parseInt(dimension[1]);
      
      else if (dimension[0].equals("colunas"))
        columns = Integer.parseInt(dimension[1]);
      
      else
        throw new UnrecognizedEntryException(line);
    }

    if (rows <= 0 || columns <= 0)
      throw new UnrecognizedEntryException("Dimensões inválidas para a folha");

    _spreadsheet = new Spreadsheet(rows, columns);  // Creates new spreadsheet with the parsed dimensions
  }


  private void parseLine(String line) throws UnrecognizedEntryException /*, more exceptions? */{
    
    String[] components = line.split("\\|");            // Separates cell coordinates from cell content

    if (components.length == 1) // do nothing
      return;
    
    if (components.length == 2) {
      String[] address = components[0].split(";");      // Parses cell's coordinates
      Content content = parseContent(components[1]);          // Parses cell's content
      _spreadsheet.insertContent(Integer.parseInt(address[0]), Integer.parseInt(address[1]), content);
    } else
      throw new UnrecognizedEntryException("Wrong format in line: " + line);
  }


  // parse the begining of an expression
  Content parseContent(String contentSpecification) {
    char c = contentSpecification.charAt(0);

    if (c == '=')
      parseContentExpression(contentSpecification.substring(1));
    else
      parseLiteral(contentSpecification);
  }


  private Literal<?> parseLiteral(String literalExpression) throws UnrecognizedEntryException {
    
    if (literalExpression.charAt(0) == '\'')        // Literal is a string
      return new LiteralString(literalExpression);
    
      else {
      try {
        int val = Integer.parseInt(literalExpression);    // Literal is an integer
        return new LiteralInteger(val);
      } catch (NumberFormatException nfe) {
        throw new UnrecognizedEntryException("Número inválido: " + literalExpression); // exception thrown if parseInt fails
      }
    }
  }

  // contentSpecification is what comes after '='
  private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException /*more exceptions */ {
    if (contentSpecification.contains("("))
      return parseFunction(contentSpecification);
    
      // It is a reference
    else{
      String[] address = contentSpecification.split(";");
      return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]), _spreadsheet);
    }
  }

  private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException /*more exceptions */ {
    String[] components = functionSpecification.split("[()]");
    if (components[1].contains(","))
      return parseBinaryFunction(components[0], components[1]);
        
    return parseIntervalFunction(components[0], components[1]);
  }

  private Content parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException /* , more Exceptions */ {
    String[] arguments = args.split(",");
    Content arg0 = parseArgumentExpression(arguments[0]);
    Content arg1 = parseArgumentExpression(arguments[1]);
    
    return switch (functionName) {
      case "ADD" -> new Add function with (arg0, arg1);
      case "SUB" -> new Sub function with (arg0, arg1);         // FIXME implement Function class
      case "MUL" -> new Mul function with (arg0, arg1);
      case "DIV" -> new Div function with (arg0, arg1);
      default -> dar erro com função inválida: functionName ;
    };
  }

  private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
    if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
      String[] address = argExpression.split(";");
      return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]), _spreadsheet);
      // pode ser diferente do anterior em parseContentExpression
    } else
      return parseLiteral(argExpression);
  }

  private Content parseIntervalFunction(String functionName, String rangeDescription)
    throws UnrecognizedEntryException /* , more exceptions ? */ {
    Range range = _spreadsheet.buildRange(rangeDescription);
    return switch (functionName) {
      case "CONCAT" -> new Concat com range 
      case "COASLECE" -> new Coaslece com range;
      case "PRODUCT" -> new Product com range;
      case "AVERAGE" -> new Average com range;
      default -> dar erro com função inválida: functionName;
    };
  }

  /* Na classe Spreadsheet preciso de algo com a seguinte funcionalidade
  Range createRange(String range) throws ? {
    String[] rangeCoordinates;
    int firstRow, firstColumn, lastRow, lastColumn;
    
    if (range.indexOf(':') != -1) {
      rangeCoordinates = range.split("[:;]");
      firstRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = Integer.parseInt(rangeCoordinates[1]);
      lastRow = Integer.parseInt(rangeCoordinates[2]);
      lastColumn = Integer.parseInt(rangeCoordinates[3]);
    } else {
      rangeCoordinates = range.split(";");
      firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
    }

    // check if coordinates are valid
    // if yes
    return new Range with firstRow, firstColumn, lastRow, lastColumn and spreadsheet;
  }
  */
  
}
