import java.util.Scanner;

public class calculator {
	public static void main(String[] args) {
        
		Scanner scanner = new Scanner(System.in);
		System.out.println("Simple Calculator");
		System.out.println("Enter expressions like: 2 + 2");
		System.out.println("Type 'exit' to quit.");

		while (true) {
			System.out.print("> ");
			if (!scanner.hasNextLine()) break;
			String line = scanner.nextLine().trim();
			if (line.equalsIgnoreCase("exit")) break;
			if (line.isEmpty()) continue;
			try {
				double result = eval(line);
				System.out.println(result);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		scanner.close();
	}

	static double eval(String s) throws Exception {
		s = s.replaceAll(",", "");
		String[] parts = s.split("\\s+");
		if (parts.length == 3) {
			double a = Double.parseDouble(parts[0]);
			String op = parts[1];
			double b = Double.parseDouble(parts[2]);
			switch (op) {
				case "+":
					return a + b;
				case "-":
					return a - b;
				case "*":
				case "x":
				case "X":
					return a * b;
				case "/":
					return a / b;
				case "%":
					return a % b;
				default:
					throw new Exception("Unsupported operator: " + op);
			}
		} else {
			return Double.parseDouble(s);
		}
	}
}
