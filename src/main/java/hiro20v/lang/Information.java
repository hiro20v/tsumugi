package hiro20v.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Information {

	private String name;
	private final Map<String, String> properties = new TreeMap<>();
	private final List<Information> childInformations = new ArrayList<>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the properties
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * @return the childInformations
	 */
	public List<Information> getChildInformations() {
		return childInformations;
	}

	@Override
	public String toString() {

		final int propertyKeyLength = getProperties().keySet().stream().mapToInt(s -> s.length()).max().orElse(0);

		final StringBuilder builder = new StringBuilder();

		builder.append("+ <").append(getName()).append(">\n");

		builder.append("  +").append(" Properties").append("\n");
		this.getProperties().entrySet().forEach(entry -> {

			final String propertyLeftSide = !getChildInformations().isEmpty() ? "  | - " : "    - ";
			builder.append(propertyLeftSide).append(join(" ", propertyKeyLength - entry.getKey().length())).append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
		});

		if (!getChildInformations().isEmpty()) {

			builder.append("  +").append(" Childlen").append("\n");
			for (int i = 0; i < getChildInformations().size(); i++) {

				final Information childInformation = getChildInformations().get(i);
				final String[] childInformationLines = childInformation.toString().split("\n");

				final boolean isNotLast = i < getChildInformations().size() - 1;
				for (int j = 0; j < childInformationLines.length; j++) {

					String childInformationLine = childInformationLines[j];
					if (isNotLast && ' ' == childInformationLine.charAt(0)) {

						childInformationLine = childInformationLine.replaceFirst("^.", "|");
					}

					builder.append("     ").append(childInformationLine).append("\n");
				}
			}
		}

		return builder.toString();
	}

	private static String join(final String s, final int count) {

		return IntStream.range(0, count).mapToObj(i -> s).collect(Collectors.joining());
	}
}
