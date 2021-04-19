package net.datenwerke.rs.terminal.client.terminal.dto.decorator;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto;
import net.datenwerke.rs.terminal.client.terminal.helper.DisplayHelper;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Dto Decorator for {@link CommandResultEntryDto}
 *
 */
abstract public class CommandResultEntryDtoDec extends CommandResultEntryDto {


	private static final long serialVersionUID = 1L;

	public CommandResultEntryDtoDec() {
		super();
	}

	public void format(DisplayHelper displayHelper, SafeHtmlBuilder builder) {
		
	}

}
