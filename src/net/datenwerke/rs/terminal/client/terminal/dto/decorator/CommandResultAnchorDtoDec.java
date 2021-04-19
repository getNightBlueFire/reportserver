package net.datenwerke.rs.terminal.client.terminal.dto.decorator;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto;
import net.datenwerke.rs.terminal.client.terminal.helper.DisplayHelper;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Dto Decorator for {@link CommandResultAnchorDto}
 *
 */
public class CommandResultAnchorDtoDec extends CommandResultAnchorDto {


	private static final long serialVersionUID = 1L;

	public CommandResultAnchorDtoDec() {
		super();
	}

	public void format(DisplayHelper displayHelper, SafeHtmlBuilder builder) {
		/* double dispatch */
		displayHelper.format(this,builder);
	}


}
