package seedu.foodrem.logic.commands.itemcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.DELETE_COMMAND;

import java.util.List;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;

/**
 * Deletes an item identified using it's displayed index from FoodRem.
 */
public class DeleteCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Deleted Item:\n%1$s";

    private final Index index;

    public DeleteCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getCurrentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
        }

        Item itemToDelete = lastShownList.get(index.getZeroBased());
        model.deleteItem(itemToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, itemToDelete));
    }

    public static String getUsage() {
        return DELETE_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && index.equals(((DeleteCommand) other).index)); // state check
    }
}
